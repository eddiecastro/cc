package com.curry.sldemo.service;

import com.curry.sldemo.model.PeopleResponseModel;
import com.curry.sldemo.model.metadata.MetadataResponseModel;
import com.curry.sldemo.model.metadata.PagingMetadataResponseModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PeopleRestServiceImplTest {

    private static final int REQUESTED_PAGE = 1;
    private static final int PAGE_SIZE = 100;
    private static final String API_URL = "http://localhost";

    @Mock
    RestTemplate restTemplate;

    @Mock
    PeopleService peopleService;

    @InjectMocks
    private PeopleRestServiceImpl systemUnderTest;

    @Before
    public void init() {
        systemUnderTest = new PeopleRestServiceImpl(restTemplate, peopleService);
        systemUnderTest.apiUrl = API_URL;
        initMocks(this);
    }

    @Test
    public void getPeopleTest() {
        PeopleResponseModel expected = new PeopleResponseModel();
        ResponseEntity<PeopleResponseModel> responseModel = new ResponseEntity<>(expected, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
                .thenReturn(responseModel);

        PeopleResponseModel actual = systemUnderTest.getPeople(REQUESTED_PAGE, PAGE_SIZE);
        assertEquals(actual, expected);
    }

    @Test
    public void getPeopleEmailCharacterFrequencyCountTest() {
        final int totalPages = 1;

        PeopleResponseModel peopleResponseModel = mock(PeopleResponseModel.class);
        MetadataResponseModel metaDataResponseModel = mock(MetadataResponseModel.class);
        PagingMetadataResponseModel pagingMetadataResponseModel = mock(PagingMetadataResponseModel.class);

        when(pagingMetadataResponseModel.getTotalPages()).thenReturn(totalPages);
        when(metaDataResponseModel.getPaging()).thenReturn(pagingMetadataResponseModel);
        when(peopleResponseModel.getMetadataResponseModel()).thenReturn(metaDataResponseModel);

        ResponseEntity<PeopleResponseModel> responseModel = new ResponseEntity<>(peopleResponseModel, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
                .thenReturn(responseModel);

        Map<String, Integer> frequencyMap = systemUnderTest.getPeopleEmailCharacterFrequencyCount();

        //verify rest call and service calls are only made a single time
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(), any(Class.class));
        verify(peopleService, times(1)).getEmailCharacterFrequencyCountFromPeopleList(anyList());
        assertNotNull(frequencyMap);
    }

    @Test
    public void getPeopleEmailCharacterFrequencyCountTest_multiplePages() {
        final int totalPages = 2;

        PeopleResponseModel peopleResponseModel = mock(PeopleResponseModel.class);
        MetadataResponseModel metaDataResponseModel = mock(MetadataResponseModel.class);
        PagingMetadataResponseModel pagingMetadataResponseModel = mock(PagingMetadataResponseModel.class);

        when(pagingMetadataResponseModel.getTotalPages()).thenReturn(totalPages);
        when(metaDataResponseModel.getPaging()).thenReturn(pagingMetadataResponseModel);
        when(peopleResponseModel.getMetadataResponseModel()).thenReturn(metaDataResponseModel);

        ResponseEntity<PeopleResponseModel> responseModel = new ResponseEntity<>(peopleResponseModel, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
                .thenReturn(responseModel);

        Map<String, Integer> frequencyMap = systemUnderTest.getPeopleEmailCharacterFrequencyCount();

        //verify rest call is called twice (matching total number of pages), but service only once to get frequencies
        verify(restTemplate, times(2)).exchange(anyString(), any(HttpMethod.class), any(), any(Class.class));
        verify(peopleService, times(1)).getEmailCharacterFrequencyCountFromPeopleList(anyList());
        assertNotNull(frequencyMap);
    }
}
