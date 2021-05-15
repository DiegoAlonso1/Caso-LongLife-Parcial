package com.acme.longlife.service;

import com.acme.longlife.domain.model.BigTree;
import com.acme.longlife.domain.repository.BigTreeRepository;
import com.acme.longlife.domain.service.BigTreeService;
import com.acme.longlife.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BigTreeServiceImplTest {
    @MockBean
    private BigTreeRepository bigTreeRepository;
    @Autowired
    private BigTreeService bigTreeService;

    @TestConfiguration
    static class BigTreeServiceImplTestConfiguration{
        @Bean
        public BigTreeService bigTreeService(){
            return new BigTreeServiceImpl();
        }
    }

    //GET ALL BIG TREES
    @Test
    @DisplayName("When GetAllBigTrees Then Returns PageOfBigTrees")
    public void whenGetAllBigTreesThenReturnsPageOfBigTrees(){
        //Arrange
        Pageable pageable = DefaultPageable();

        /*BigTrees*/
        List<BigTree> bigTrees = List.of(
                CreateValidBigTree(1L, "user1", "email1"),
                CreateValidBigTree(2L, "user2", "email2"),
                CreateValidBigTree(3L, "user3", "email3"),
                CreateValidBigTree(4L, "user4", "email4"),
                CreateValidBigTree(5L, "user5", "email5")
        );
        Page<BigTree> bigTreePage = new PageImpl<>(bigTrees, pageable, bigTrees.size());

        when(bigTreeRepository.findAll(pageable)).thenReturn(bigTreePage);

        //Act
        Page<BigTree> foundBigTrees = bigTreeService.getAllBigTrees(pageable);
        //Assert
        assertThat(foundBigTrees)
                .isEqualTo(bigTreePage)
                .contains(bigTrees.get(0),
                        bigTrees.get(1),
                        bigTrees.get(2),
                        bigTrees.get(3),
                        bigTrees.get(4));
    }

    //GET BIG TREE BY ID VALID ID
    @Test
    @DisplayName("When GetBigTreeById With Valid Id Then Returns BigTree")
    public void whenGetBigTreeByIdWithValidIdThenReturnsBigTree(){
        //Arrange
        BigTree bigTree = DefaultBigTree(null);
        Long id = bigTree.getId();
        when(bigTreeRepository.findById(id)).thenReturn(Optional.of(bigTree));

        //Act
        BigTree foundBigTree = bigTreeService.getBigTreeById(id);

        //Assert
        assertThat(foundBigTree.getId()).isEqualTo(id);
    }

    //GET BIG TREE BY ID INVALID ID
    @Test
    @DisplayName("When GetBigTreeById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetBigTreeByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        when(bigTreeRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = ResourceNotFoundMessage("BigTree", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            BigTree foundBigTree = bigTreeService.getBigTreeById(id);
        });

        //Assert
        assertThat(exception)
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(expectedMessage);
    }

    //CREATE BIG TREE VALID AGE
    @Test
    @DisplayName("When CreateBigTree With Valid Age Then Returns BigTree")
    public void whenCreateBigTreeWithValidAgeThenReturnsBigTree(){
        //Arrange
        Calendar date = Calendar.getInstance();
        date.set(1971, 05, 14);

        BigTree bigTree = DefaultBigTree(date);

        when(bigTreeRepository.save(bigTree)).thenReturn(bigTree);

        //Act
        BigTree foundBigTree = bigTreeService.createBigTree(bigTree);

        //Assert
        assertThat(foundBigTree).isEqualTo(bigTree);
    }

    //CREATE BIG TREE INVALID AGE
    @Test
    @DisplayName("When CreateBigTree With Invalid Age Then Returns RuntimeException")
    public void whenCreateBigTreeWithInvalidAgeThenReturnsRuntimeException(){
        //Arrange
        Calendar date = Calendar.getInstance();
        date.set(1971, 05, 15);
        String expectedMessage = InvalidAgeMessage();

        BigTree bigTree = DefaultBigTree(date);

        //Act
        Throwable exception = catchThrowable(() -> {
            BigTree foundBigTree = bigTreeService.createBigTree(bigTree);
        });

        //Assert
        assertThat(exception).
                isInstanceOf(RuntimeException.class)
                .hasMessage(expectedMessage);
    }

    //UPDATE BIG TREE VALID ID AND AGE
    @Test
    @DisplayName("When UpdateBigTree With Valid Id And Age Then Returns BigTree")
    public void whenUpdateBigTreeWithValidIdAndAgeThenReturnsBigTree(){
        //Arrange
        Calendar date = Calendar.getInstance();
        date.set(1971, 05, 14);

        BigTree bigTree = DefaultBigTree(date);
        Long id = bigTree.getId();

        when(bigTreeRepository.findById(id)).thenReturn(Optional.of(bigTree));
        when(bigTreeRepository.save(bigTree)).thenReturn(bigTree);

        //Act
        BigTree foundBigTree = bigTreeService.updateBigTree(id, bigTree);

        //Assert
        assertThat(foundBigTree).isEqualTo(bigTree);
    }

    //UPDATE BIG TREE INVALID ID
    @Test
    @DisplayName("When UpdateBigTree With Invalid Id Then Returns ResourceNotFoundException")
    public void whenUpdateBigTreeWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Calendar date = Calendar.getInstance();
        date.set(1971, 05, 14);

        BigTree bigTree = DefaultBigTree(date);
        Long id = bigTree.getId();
        String expectedMessage = ResourceNotFoundMessage("BigTree", "Id", id);

        when(bigTreeRepository.save(bigTree)).thenReturn(bigTree);

        //Act
        Throwable exception = catchThrowable(() -> {
            BigTree foundBigTree = bigTreeService.updateBigTree(id, bigTree);
        });

        //Assert
        assertThat(exception).
                isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    //UPDATE BIG TREE INVALID AGE
    @Test
    @DisplayName("When UpdateBigTree With Invalid Age Then Returns RuntimeException")
    public void whenUpdateBigTreeWithInvalidAgeThenReturnsRuntimeException(){
        //Arrange
        Calendar date = Calendar.getInstance();
        date.set(1971, 05, 15);

        BigTree bigTree = DefaultBigTree(date);
        Long id = bigTree.getId();
        String expectedMessage = InvalidAgeMessage();

        when(bigTreeRepository.save(bigTree)).thenReturn(bigTree);

        //Act
        Throwable exception = catchThrowable(() -> {
            BigTree foundBigTree = bigTreeService.updateBigTree(id, bigTree);
        });

        //Assert
        assertThat(exception).
                isInstanceOf(RuntimeException.class)
                .hasMessage(expectedMessage);
    }

    //DELETE BIG TREE VALID ID
    @Test
    @DisplayName("When DeleteBigTree With Valid Id Then Returns ResponseEntity.Ok")
    public void whenDeleteBigTreeWithValidIdThenReturnsResponseEntityOk(){
        //Arrange

        BigTree bigTree = DefaultBigTree(null);
        Long id = bigTree.getId();

        when(bigTreeRepository.findById(id)).thenReturn(Optional.of(bigTree));
        //doNothing().when(bigTreeRepository).delete(bigTree);

        //Act
        ResponseEntity<?> result = bigTreeService.deleteBigTree(id);

        //Assert
        assertThat(result)
            .isInstanceOf(ResponseEntity.ok().build().getClass());
    }

    //DELETE BIG TREE INVALID ID
    @Test
    @DisplayName("When DeleteBigTree With Invalid Id Then Returns ResourceNotFoundException")
    public void whenDeleteBigTreeWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        BigTree bigTree = DefaultBigTree(null);
        Long id = bigTree.getId();
        String expectedMessage = ResourceNotFoundMessage("BigTree", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            bigTreeService.deleteBigTree(id);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    private BigTree DefaultBigTree(Calendar date){
        BigTree bigTree = new BigTree()
                .setId(1L)
                .setUsername("user")
                .setEmail("email")
                .setGender("gender")
                .setFirstName("first")
                .setLastName("last");
        if (date != null)
            return bigTree.setBornAt(date);
        return bigTree;
    }

    private String ResourceNotFoundMessage(String resourceName, String fieldName, Object fieldValue){
        return String.format("Resource %s not found for %s with value %s",
                resourceName, fieldName, fieldValue);
    }

    private String InvalidAgeMessage(){
        return "The expected age must be over 50";
    }

    private Pageable DefaultPageable(){
        return Pageable.unpaged();
    }

    private BigTree CreateValidBigTree(Long id, String userName, String email)
    {
        Calendar date = Calendar.getInstance();
        date.set(1970, 05, 14);
        return new BigTree()
                .setId(id)
                .setUsername(userName)
                .setEmail(email)
                .setGender("gender")
                .setFirstName("first")
                .setLastName("last")
                .setBornAt(date);
    }
}