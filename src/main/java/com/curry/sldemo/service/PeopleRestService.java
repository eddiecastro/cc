package com.curry.sldemo.service;

import com.curry.sldemo.model.PeopleResponseModel;
import com.curry.sldemo.model.PersonDuplicate;

import java.util.List;
import java.util.Map;

public interface PeopleRestService {

    PeopleResponseModel getPeople(int requestedPage, int pageSize);
    Map<String, Integer> getPeopleEmailCharacterFrequencyCount();
    List<PersonDuplicate> getPossibleDuplicates();
}
