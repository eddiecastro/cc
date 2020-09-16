package com.curry.sldemo.service;

import com.curry.sldemo.model.EmailCharacterFrequency;
import com.curry.sldemo.model.PeopleResponseModel;
import com.curry.sldemo.model.PersonDuplicate;

import java.util.List;

public interface PeopleRestService {

    PeopleResponseModel getPeople(int requestedPage, int pageSize);
    List<EmailCharacterFrequency> getPeopleEmailCharacterFrequencyCount();
    List<PersonDuplicate> getPossibleDuplicates();
}
