package com.acme.longlife.controller;

import com.acme.longlife.controller.BigTreesController;
import com.acme.longlife.domain.model.BigTree;
import com.acme.longlife.domain.service.BigTreeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BigTreesController.class)
public class BigTreeControllerTest {
    @MockBean
    private BigTreeService bigTreeService;
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGetAllBigTreesThenReturnsAllBigTrees(){
//        //Arrange
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/big_trees");
//        MvcResult result = mvc.perform(requestBuilder).andReturn();
//        //Act
//        //Assert
//        assertThat("Hello World").isEqualTo(result.getResponse().getContentAsString());
    }
}
