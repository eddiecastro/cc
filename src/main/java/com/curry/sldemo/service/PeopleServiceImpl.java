package com.curry.sldemo.service;

import com.curry.sldemo.model.Person;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Override
    public Map<String, Integer> getEmailCharacterFrequencyCountFromPeopleList(List<Person> peopleList) {
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        for(Person person : peopleList) {
            processEmail(frequencyMap, person.getEmailAddress());
        }

        return frequencyMap;
    }

    private void processEmail(HashMap<String, Integer> frequencyMap, String emailAddress) {
        for(int i = 0; i < emailAddress.length(); i++) {
            //NOTE: the assignment only said unique "character", so I'm leaving the count of symbols in the
            //frequency map and letting capitial and non capital be unique characters
            String letter = String.valueOf(emailAddress.charAt(i));

            if(frequencyMap.containsKey(letter)) {
                int currentCount = frequencyMap.get(letter);
                frequencyMap.put(letter, ++currentCount);
            } else {
                frequencyMap.put(letter, 1);
            }
        }
    }
}
