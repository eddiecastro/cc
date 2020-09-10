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

    private static final int PAGE_SIZE_BELOW_MIN = 0;
    private static final int PAGE_SIZE_ABOVE_MAX = 101;
    private static final int INVALID_PAGE_NUMBER = 0;
    private static final int VALID_PAGE_NUMBER = 1;
    private static final int VALID_PAGE_SIZE = 100;

    @MockBean
    PeopleService peopleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPeopleList() throws Exception {
        when(peopleService.getPeople(VALID_PAGE_NUMBER, VALID_PAGE_SIZE)).thenReturn(new PeopleResponseModel());

        MvcResult result = mockMvc.perform(
                get("/people")
                    .queryParam("page", String.valueOf(VALID_PAGE_NUMBER))
                    .queryParam("page_size", String.valueOf(VALID_PAGE_SIZE)))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        PeopleResponseModel response = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {});

        verify(peopleService).getPeople(VALID_PAGE_NUMBER, VALID_PAGE_SIZE);
        assertNotNull(response);
    }

    @Test
    public void testGetPeopleList_pageSizeExceedsMaximum() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/people")
                    .queryParam("page", String.valueOf(VALID_PAGE_NUMBER))
                    .queryParam("page_size", String.valueOf(PAGE_SIZE_ABOVE_MAX)))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(peopleService, never()).getPeople(anyInt(), anyInt());
    }

    @Test
    public void testGetPeopleList_pageSizeBelowMinimum() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/people")
                        .queryParam("page", String.valueOf(VALID_PAGE_NUMBER))
                        .queryParam("page_size", String.valueOf(PAGE_SIZE_BELOW_MIN)))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(peopleService, never()).getPeople(anyInt(), anyInt());
    }

    @Test
    public void testGetPeopleList_invalidPageNumber() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/people")
                        .queryParam("page", String.valueOf(INVALID_PAGE_NUMBER))
                        .queryParam("page_size", String.valueOf(VALID_PAGE_SIZE)))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(peopleService, never()).getPeople(anyInt(), anyInt());
    }
}
