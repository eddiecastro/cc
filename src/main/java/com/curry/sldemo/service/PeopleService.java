package com.curry.sldemo.service;

import com.curry.sldemo.model.Person;

import java.util.List;
import java.util.Map;

public interface PeopleService {

    Map<String, Integer> getEmailCharacterFrequencyCountFromPeopleList(List<Person> peopleList);
}
