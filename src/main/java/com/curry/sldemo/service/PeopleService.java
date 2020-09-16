package com.curry.sldemo.service;

import com.curry.sldemo.model.EmailCharacterFrequency;
import com.curry.sldemo.model.Person;
import com.curry.sldemo.model.PersonDuplicate;

import java.util.List;

public interface PeopleService {

    List<EmailCharacterFrequency> getEmailCharacterFrequencyCountFromPeopleList(List<Person> peopleList);
    List<PersonDuplicate> getPossibleDuplicatesFromList(List<Person> peopleList);
}
