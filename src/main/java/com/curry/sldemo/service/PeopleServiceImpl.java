package com.curry.sldemo.service;

import com.curry.sldemo.model.PeopleResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final static String API_ENDPOINT = "/people.json";

    @Value("${SL_DEMO_API_KEY}")
    private String apiKey;

    @Value("${api.endpoint.url}")
    String apiUrl;

    private RestTemplate restTemplate;

    public PeopleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PeopleResponseModel getPeople(int requestedPage, int pageSize) {
        ResponseEntity<PeopleResponseModel> response = restTemplate.exchange(
                buildUrlWithParameters(apiUrl + API_ENDPOINT, requestedPage, pageSize),
                HttpMethod.GET,
                getHttpEntity(),
                PeopleResponseModel.class
        );

        return response.getBody();
    }

    //TODO: this, apiUrl, and apiKey could be placed in a shared place with ClientHttpRequestInterceptor if a second controller is needed
    //TODO: at the very least these could be placed in a baseController class, they don't belong here
    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add( "Authorization", "Bearer " + apiKey);

        return new HttpEntity(headers);
    }

    private String buildUrlWithParameters(String url, int requestedPage, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("include_paging_counts", true)
                .queryParam("page", requestedPage)
                .queryParam("per_page", pageSize);

        return builder.toUriString();
    }
}
