package com.curry.sldemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final static String API_ENDPOINT = "/people.json";

    @Value("${SL_DEMO_API_URL}")
    private String apiUrl;

    @Value("${SL_DEMO_API_KEY}")
    private String apiKey;

    private RestTemplate restTemplate;

    public PeopleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getPeople() {
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl + API_ENDPOINT,
                HttpMethod.GET,
                getHttpEntity(),
                String.class
        );

        String people1 = "people1";
        String people2 = "people2";
        List<String> people = new ArrayList<>();
        people.add(people1);
        people.add(people2);
        return people;
    }

    //TODO: this, apiUrl, and apiKey could be placed in a shared place with ClientHttpRequestInterceptor if a second controller is needed
    //TODO: at the very least these could be placed in a baseController class, they don't belong here
    private HttpEntity getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add( "Authorization", "Bearer " + apiKey);

        // build the request
        return new HttpEntity(headers);
    }
}
