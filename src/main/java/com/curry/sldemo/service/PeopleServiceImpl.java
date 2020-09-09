package com.curry.sldemo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    private RestTemplate restTemplate;

    public PeopleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getPeople() {
        String people1 = "people1";
        String people2 = "people2";
        List<String> people = new ArrayList<>();
        people.add(people1);
        people.add(people2);
        return people;
    }
}
