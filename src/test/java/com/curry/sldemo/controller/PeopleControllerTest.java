package com.curry.sldemo.controller;

import com.curry.sldemo.model.PeopleResponseModel;
import com.curry.sldemo.service.PeopleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {

    private static final int REQUESTED_PAGE = 1;

    @MockBean
    PeopleService peopleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPeopleList() throws Exception {
        when(peopleService.getPeople(REQUESTED_PAGE)).thenReturn(new PeopleResponseModel());

        MvcResult result = mockMvc.perform(get("/people").param("page", "1"))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        PeopleResponseModel response = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {});

        verify(peopleService).getPeople(REQUESTED_PAGE);
        assertNotNull(response);
    }
}
