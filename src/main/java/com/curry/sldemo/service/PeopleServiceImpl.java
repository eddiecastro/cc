package com.curry.sldemo.service;

import com.curry.sldemo.model.EmailCharacterFrequency;
import com.curry.sldemo.model.Person;
import com.curry.sldemo.model.PersonDuplicate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeopleServiceImpl implements PeopleService {

    //what percent of the overall string length can we tolerate as being different, but still close enough to call a potential duplicate
    private static final float INCONSISTENCY_COUNT_LIMIT_PERCENTAGE = 0.20f;

    @Override
    public List<EmailCharacterFrequency> getEmailCharacterFrequencyCountFromPeopleList(List<Person> peopleList) {
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        for(Person person : peopleList) {
            processEmailCharacterFrequency(frequencyMap, person.getEmailAddress());
        }

        List<EmailCharacterFrequency> emailCharacterFrequencies = new ArrayList<>();
        for(String key : frequencyMap.keySet()) {
            emailCharacterFrequencies.add(new EmailCharacterFrequency(key, frequencyMap.get(key)));
        }

        return emailCharacterFrequencies;
    }

    @Override
    public List<PersonDuplicate> getPossibleDuplicatesFromList(List<Person> peopleList) {
        List<PersonDuplicate> possibleDuplicates = new ArrayList<>();

        // for each person we want to check for a duplicate against each other person
        // in a production env, this would be done differently. We may want to delegate to another service that
        // could execute a SQL query for a specific person or check for duplicates upon the addition of a "Person"
        //either way, its not maintainable to check duplicates against so many records (if the number of records ends up being in the thousands or more)
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
            //frequency map and letting capital and non capital be unique characters
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
     * and since this is measuring potential duplicates instead of similarity in general,
     * I thought it was better to check character by character to make sure ordering was mostly correct as well as similarity
     * If too many inconsistencies are found early on in the string, I don't see a reason to continue looking
     */
    private void processDuplicate(Person person, Person possibleDuplicate, List<PersonDuplicate> possibleDuplicates) {
        String email1 = person.getEmailAddress();
        String email2 = possibleDuplicate.getEmailAddress();

        //email1 should be the longer string for the algorithm below
        if(email1.length() < email2.length()) {
            String temp = email1;
            email1 = email2;
            email2 = temp;
        }

        int difference = email1.length() - email2.length();
        int incorrectCount = 0;

        //allow a larger number of differences to occur for larger strings
        int maxDifferencesForPotentialDuplicate = (int) (INCONSISTENCY_COUNT_LIMIT_PERCENTAGE * email1.length());
        if(difference <= maxDifferencesForPotentialDuplicate) {
            int email1Index = 0;
            int email2Index = 0;
            while(email1Index < email1.length() && email2Index < email2.length()) {

                if(email1.charAt(email1Index) == email2.charAt(email2Index)) {
                    //if the characters match, move on to the next characters in the string
                    email1Index++;
                    email2Index++;
                } else {
                    /**
                     * NOTE: this won't capture all possible duplicates, just enough to present in the client application
                     * I recognize that this isn't the complete answer, but I wanted to avoid copying an
                     * existing algorithm/focus on getting the whole package functioning
                     * This won't capture duplicates of strings of the same length
                     * Given more time I could look into using an existing algorithms or focus more on improving this one
                     * possible solutions include checking longest common subsequence (or a combination of them) or cosine similarity, etc.
                     */
                    //if the characters don't match, increase incorrect count
                    //currryyyy
                    //currtyyy
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
