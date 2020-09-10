package com.curry.sldemo.service;

import com.curry.sldemo.model.PeopleResponseModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PeopleServiceImplTest {

    private static final int REQUESTED_PAGE = 1;
    private static final String API_URL = "http://localhost";

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private PeopleServiceImpl systemUnderTest;

    @Before
    public void init() {
        systemUnderTest = new PeopleServiceImpl(restTemplate);
        systemUnderTest.apiUrl = API_URL;
        initMocks(this);
    }

    @Test
    public void getPeopleTest() {
        PeopleResponseModel expected = new PeopleResponseModel();
        ResponseEntity<PeopleResponseModel> responseModel = new ResponseEntity<>(expected, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
                .thenReturn(responseModel);

        PeopleResponseModel actual = systemUnderTest.getPeople(REQUESTED_PAGE);
        assertEquals(actual, expected);
    }
}
