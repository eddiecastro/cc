package com.curry.sldemo.service;

import com.curry.sldemo.model.PeopleResponseModel;

import java.util.Map;

public interface PeopleRestService {

    PeopleResponseModel getPeople(int requestedPage, int pageSize);
    Map<String, Integer> getPeopleEmailCharacterFrequencyCount();
}
