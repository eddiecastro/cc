package com.curry.sldemo.service;

import com.curry.sldemo.model.PeopleResponseModel;
import com.curry.sldemo.model.Person;
import com.curry.sldemo.model.metadata.MetadataResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PeopleRestServiceImpl implements PeopleRestService {

    private static final String API_ENDPOINT = "/people.json";
    private static final int MAX_PAGE_SIZE = 100;

    @Value("${SL_DEMO_API_KEY}")
    private String apiKey;

    @Value("${api.endpoint.url}")
    String apiUrl;

    private RestTemplate restTemplate;
    private PeopleService peopleService;

    public PeopleRestServiceImpl(RestTemplate restTemplate, PeopleService peopleService) {
        this.restTemplate = restTemplate;
        this.peopleService = peopleService;
    }

    @Override
    public PeopleResponseModel getPeople(int requestedPage, int pageSize) {
        String url = apiUrl + API_ENDPOINT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("include_paging_counts", true)
                .queryParam("page", requestedPage)
                .queryParam("per_page", pageSize);

        ResponseEntity<PeopleResponseModel> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                getHttpEntity(),
                PeopleResponseModel.class
        );

        return response.getBody();
    }

    @Override
    public Map<String, Integer> getPeopleEmailCharacterFrequencyCount() {
        List<Person> peopleList = this.getAllAvailablePeople();
        return peopleService.getEmailCharacterFrequencyCountFromPeopleList(peopleList);
    }

    //NOTE: This would need to re-evaluated in a production env. peopleList could get too big, but I know we only have access to 351 for the assessment
    //A better solution would be to break this up into chunks (only pull ~1000 at a time, update frequency map, repeat)
    private List<Person> getAllAvailablePeople() {
        List<Person> peopleList = new ArrayList<>();
        int currentPage = 1;
        int pageSize = MAX_PAGE_SIZE;

        PeopleResponseModel response = this.getPeople(currentPage, pageSize);
        MetadataResponseModel metadata = response.getMetadataResponseModel();

        int totalPages = metadata.getPaging().getTotalPages();
        currentPage += 1;
        peopleList.addAll(response.getPeople());

        while(currentPage <= totalPages) {
            response = this.getPeople(currentPage, pageSize);
            peopleList.addAll(response.getPeople());
            currentPage += 1;
        }

        return peopleList;
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add( "Authorization", "Bearer " + apiKey);

        return new HttpEntity(headers);
    }
}
