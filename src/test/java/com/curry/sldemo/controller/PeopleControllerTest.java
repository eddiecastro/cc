package com.curry.sldemo.controller;

import com.curry.sldemo.service.PeopleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {

    @MockBean
    PeopleService peopleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPeopleList() throws Exception {
        List<String> expected = new ArrayList<>();
        expected.add("person1");

        when(peopleService.getPeople()).thenReturn(expected);

        MvcResult result = mockMvc.perform(get("/people"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();

        List<String> actual = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<String>>() {});

        assertEquals(actual.size(), 1);
        assertEquals(actual.get(0), "person1");
    }
}
