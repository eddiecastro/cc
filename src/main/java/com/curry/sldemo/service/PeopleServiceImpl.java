package com.curry.sldemo.service;

import com.curry.sldemo.model.Person;
import com.curry.sldemo.model.PersonDuplicate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeopleServiceImpl implements PeopleService {

    //what percent of the overall string length can we tolerate as being different, but still close enough to call a potential duplicate
    private static final float INCONSISTENCY_COUNT_LIMIT_PERCENTAGE = 0.20f;

    @Override
    public Map<String, Integer> getEmailCharacterFrequencyCountFromPeopleList(List<Person> peopleList) {
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        for(Person person : peopleList) {
            processEmailCharacterFrequency(frequencyMap, person.getEmailAddress());
        }

        return frequencyMap;
    }

    @Override
    public List<PersonDuplicate> getPossibleDuplicatesFromList(List<Person> peopleList) {
        List<PersonDuplicate> possibleDuplicates = new ArrayList<>();

        // for each person we want to check for a duplicate against each other person
        // in a production env, this would be done differently. We may want to delegate to another service that
        // could execute a SQL query for a specific person or check for duplicates upon the addition of a "Person" and mark it as such
        for(int i = 0; i < peopleList.size(); i++) {
            for(int j = i+1; j < peopleList.size(); j++) {
                processDuplicate(peopleList.get(i), peopleList.get(j), possibleDuplicates);
            }
        }

        return possibleDuplicates;
    }

    private void processEmailCharacterFrequency(HashMap<String, Integer> frequencyMap, String emailAddress) {
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

    /**
     * NOTES:
     * There are algorithms to get string similarity. I didn't want to just copy one
     * and since this is measuring potential duplicates instead of similarity as a whole,
     * I thought it was better to check character by character to make sure ordering was mostly correct
     * If too many inconsistencies are found early on in the string, I don't see a reason to continue looking
     * Even if curry and currrrrrrrry are very similar and are the same word, I don't think the second would have been entered in by mistake
     */
    private void processDuplicate(Person person, Person possibleDuplicate, List<PersonDuplicate> possibleDuplicates) {
        String email1 = person.getEmailAddress();
        String email2 = possibleDuplicate.getEmailAddress();

        if(email1.length() < email2.length()) {
            String temp = email1;
            email1 = email2;
            email2 = temp;
        }


        int difference = email1.length() - email2.length();
        int incorrectCount = 0;

        int maxDifferencesForPotentialDuplicate = (int) (INCONSISTENCY_COUNT_LIMIT_PERCENTAGE * email1.length());
        if(difference <= maxDifferencesForPotentialDuplicate) {
            int email1Index = 0;
            int email2Index = 0;
            while(email1Index < email1.length() && email2Index < email2.length()) {

                if(email1.charAt(email1Index) == email2.charAt(email2Index)) {
                    email1Index++;
                    email2Index++;
                } else {
                    incorrectCount++;
                    email1Index++;
                }
            }

            if(incorrectCount <= maxDifferencesForPotentialDuplicate) {
                possibleDuplicates.add(new PersonDuplicate(person, possibleDuplicate));
            }
        }
    }

}
