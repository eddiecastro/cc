package com.curry.sldemo.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class PeopleServiceImplTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private PeopleServiceImpl systemUnderTest;

    @Before
    public void init() {
        systemUnderTest = new PeopleServiceImpl(restTemplate);
        initMocks(this);
    }

    @Test
    public void getPeopleTest() {
        List<String> actual = systemUnderTest.getPeople();
        assertEquals(actual.size(), 2);
    }
}
