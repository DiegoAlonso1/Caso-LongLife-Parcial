package com.acme.longlife.service;

import com.acme.longlife.domain.model.BigTree;
import com.acme.longlife.domain.model.GreenLeaf;
import com.acme.longlife.domain.repository.BigTreeRepository;
import com.acme.longlife.domain.repository.GreenLeafRepository;
import com.acme.longlife.domain.service.GreenLeafService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GreenLeafServiceImplTest {
    @MockBean
    private GreenLeafRepository greenLeafRepository;
    @MockBean
    private BigTreeRepository bigTreeRepository;
    @Autowired
    private GreenLeafService greenLeafService;

    @TestConfiguration
    static class GreenLeafServiceImplTestConfiguration {
        @Bean
        public GreenLeafService greenLeafService(){
            return new GreenLeafServiceImpl();
        }
    }

    //GET ALL GREEN LEAVES BY BIG TREE ID
    @Test
    @DisplayName("When GetAllGreenLeavesByBigTreeId With Valid Id Then Returns PageOfGreenLeaves")
    void whenGetAllGreenLeavesByBigTreeIdThenReturnsPageOfGreenLeaves() {
        //Arrange
        Pageable pageable = DefaultPageable();

        Long bigTreeId = 1L;

        /*GreenLeaves*/
        List<GreenLeaf> greenLeaves = List.of(
            CreateValidGreenLeaf(1L, "titulo1", null),
                CreateValidGreenLeaf(2L, "titulo2", null),
                CreateValidGreenLeaf(3L, "titulo3", null),
                CreateValidGreenLeaf(4L, "titulo4", null),
                CreateValidGreenLeaf(5L, "titulo5", null)
        );
        Page<GreenLeaf> greenLeafPage = new PageImpl<>(greenLeaves, pageable, greenLeaves.size());

        when(greenLeafRepository.findAllByBigTreeId(bigTreeId, pageable)).thenReturn(greenLeafPage);

        //Act
        Page<GreenLeaf> greenLeafPageFound = greenLeafService.getAllGreenLeavesByBigTreeId(bigTreeId, pageable);

        //Assert
        assertThat(greenLeafPageFound)
                .isEqualTo(greenLeafPage)
                .contains(greenLeaves.get(0),
                        greenLeaves.get(1),
                        greenLeaves.get(2),
                        greenLeaves.get(3),
                        greenLeaves.get(4));
    }

    //GET GREEN LEAF BY ID AND BIG TREE ID VALID ID AND BIG TREE ID
    @Test
    @DisplayName("When GetGreenLeafByIdAndBigTreeId With Valid Id And BigTreeId Then Returns GreenLeaf")
    void whenGetGreenLeafByIdAndBigTreeIdThenReturnsGreenLeaf(){
        //Arrange
        GreenLeaf greenLeaf = DefaultGreenLeaf(null);
        Long greenLeafId = greenLeaf.getId();
        Long bigTreeId = 1L;

        when(greenLeafRepository.findByIdAndAndBigTreeId(greenLeafId, bigTreeId)).thenReturn(Optional.of(greenLeaf));

        //Act
        GreenLeaf greenLeafFound = greenLeafService.getGreenLeafByIdAndBigTreeId(bigTreeId, greenLeafId);

        //Assert
        assertThat(greenLeafFound).isEqualTo(greenLeaf);
    }

    //GET GREEN LEAF BY ID AND BIG TREE ID VALID ID AND BIG TREE ID
    @Test
    @DisplayName("When GetGreenLeafByIdAndBigTreeId With Valid Id And BigTreeId Then Returns GreenLeaf")
    void whenGetGreenLeafByIdAndBigTreeIdWithValidIdAndBigTreeIdThenReturnsGreenLeaf(){
        //Arrange
        GreenLeaf greenLeaf = DefaultGreenLeaf(null);
        Long greenLeafId = greenLeaf.getId();
        Long bigTreeId = 1L;

        when(greenLeafRepository.findByIdAndAndBigTreeId(greenLeafId, bigTreeId)).thenReturn(Optional.of(greenLeaf));

        //Act
        GreenLeaf greenLeafFound = greenLeafService.getGreenLeafByIdAndBigTreeId(bigTreeId, greenLeafId);

        //Assert
        assertThat(greenLeafFound).isEqualTo(greenLeaf);
    }

    //GET GREEN LEAF BY ID AND BIG TREE ID INVALID ID OR INVALID BIG TREE IDd
    @Test
    @DisplayName("When GetGreenLeafByIdAndBigTreeId With Invalid Id Or BigTreeId Then Returns ResourceNotFoundException")
    void whenGetGreenLeafByIdAndBigTreeIdWithInvalidIdOrBigTreeIdThenReturnsResourceNotFoundException(){
        //Arrange
        GreenLeaf greenLeaf = DefaultGreenLeaf(null);

        Long expectedGreenLeafId = greenLeaf.getId();
        Long greenLeafId = 5L;
        Long bigTreeId = 1L;

        String expectedMessage = "GreenLeaf not found with Id " + greenLeafId + " and BigTreeId " + bigTreeId;

        when(greenLeafRepository.findByIdAndAndBigTreeId(expectedGreenLeafId, bigTreeId)).thenReturn(Optional.of(greenLeaf));

        //Act
        Throwable exception = catchThrowable(() -> {
            GreenLeaf greenLeafFound = greenLeafService.getGreenLeafByIdAndBigTreeId(bigTreeId, greenLeafId);
        });     /*returns the same with not expected bigTreeId*/

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    //CREATE GREEN LEAF WITH VALID TITLE AND  BIG TREE ID
    @Test
    @DisplayName("When CreateGreenLeaf With Valid Title And BigTreeId Then Returns GreenLeaf")
    void whenCreateGreenLeafWithValidTitleAndBigTreeIdThenReturnsGreenLeaf(){
        //Arrange
        GreenLeaf greenLeaf = DefaultGreenLeaf(null);
        Long greenLeafId = greenLeaf.getId();
        String title = greenLeaf.getTitle();

        BigTree bigTree = DefaultBigTree(null);
        Long bigTreeId = bigTree.getId();

        when(greenLeafRepository.existsByBigTreeIdAndTitle(bigTreeId, title)).thenReturn(false);
        when(bigTreeRepository.findById(bigTreeId)).thenReturn(Optional.of(bigTree));
        when(greenLeafRepository.save(greenLeaf)).thenReturn(greenLeaf);

        //Act
        GreenLeaf greenLeafFound = greenLeafService.createGreenLeaf(bigTreeId, greenLeaf);

        //Assert
        assertThat(greenLeafFound).isEqualTo(greenLeaf);

    }

    //CREATE GREEN LEAF WITH INVALID BIG TREE ID
    @Test
    @DisplayName("When CreateGreenLeaf With Invalid BigTreeId Then Returns ResourceNotFoundException")
    void whenCreateGreenLeafWithInvalidBigTreeIdThenReturnsResourceNotFoundException(){
        //Arrange
        GreenLeaf greenLeaf = DefaultGreenLeaf(null);
        String title = greenLeaf.getTitle();

        BigTree bigTree = DefaultBigTree(null);
        Long expectedBigTreeId = bigTree.getId();
        Long bigTreeId = 5L;

        String expectedMessage = ResourceNotFoundMessage("BigTree", "Id", bigTreeId);

        when(greenLeafRepository.existsByBigTreeIdAndTitle(expectedBigTreeId, title)).thenReturn(false);
        when(bigTreeRepository.findById(expectedBigTreeId)).thenReturn(Optional.of(bigTree));

        //Act
        Throwable exception = catchThrowable(() -> {
            GreenLeaf greenLeafFound = greenLeafService.createGreenLeaf(bigTreeId, greenLeaf);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

    //CREATE GREEN LEAF WITH VALID TITLE AND  BIG TREE ID
    @Test
    @DisplayName("When CreateGreenLeaf With Invalid TitleThen Returns RuntimeException")
    void whenCreateGreenLeafWithInvalidTitleThenReturnsRuntimeException(){
        //Arrange
        GreenLeaf greenLeaf = DefaultGreenLeaf(null);
        String title = greenLeaf.getTitle();

        BigTree bigTree = DefaultBigTree(null);
        Long bigTreeId = bigTree.getId();

        String expectedMessage = InvalidTitleMessage();

        when(greenLeafRepository.existsByBigTreeIdAndTitle(bigTreeId, title)).thenReturn(true);

        //Act
        Throwable exception = catchThrowable(() -> {
            GreenLeaf greenLeafFound = greenLeafService.createGreenLeaf(bigTreeId, greenLeaf);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(expectedMessage);

    }


    private Pageable DefaultPageable(){
        return Pageable.unpaged();
    }

    private String ResourceNotFoundMessage(String resourceName, String fieldName, Object fieldValue){
        return String.format("Resource %s not found for %s with value %s",
                resourceName, fieldName, fieldValue);
    }

    private String InvalidTitleMessage(){
        return "Cannot exist two Green Leaves with same title";
    }

    private GreenLeaf DefaultGreenLeaf(BigTree bigTree){
        GreenLeaf greenLeaf = new GreenLeaf()
                .setId(1L)
                .setTitle("Titulo");
        if (bigTree != null)
            return greenLeaf.setBigTree(bigTree);
        return greenLeaf;
    }

    private GreenLeaf CreateValidGreenLeaf(Long id, String title, BigTree bigTree) {
        return new GreenLeaf()
                .setId(id)
                .setTitle(title)
                .setTip("Tip")
                .setScenario("Escenario")
                .setBigTree(bigTree);
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
}