package com.curry.sldemo.service;

import com.curry.sldemo.model.Person;
import com.curry.sldemo.model.PersonDuplicate;

import java.util.List;
import java.util.Map;

public interface PeopleService {

    Map<String, Integer> getEmailCharacterFrequencyCountFromPeopleList(List<Person> peopleList);
    List<PersonDuplicate> getPossibleDuplicatesFromList(List<Person> peopleList);
}
