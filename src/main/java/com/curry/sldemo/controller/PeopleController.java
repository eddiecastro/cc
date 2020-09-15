package com.curry.sldemo.controller;

import com.curry.sldemo.exception.PeopleListArgumentException;
import com.curry.sldemo.model.PeopleResponseModel;
import com.curry.sldemo.model.PersonDuplicate;
import com.curry.sldemo.service.PeopleRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PeopleController {

    private static final int PAGE_SIZE_MIN = 1;
    private static final int PAGE_SIZE_MAX = 100;
    private static final int PAGE_NUMBER_MIN = 1;

    private PeopleRestService peopleRestService;

    @Autowired
    public PeopleController(PeopleRestService peopleRestService) {
        this.peopleRestService = peopleRestService;
    }

    //TODO: logging
    @GetMapping("/people")
    public ResponseEntity<PeopleResponseModel> getPeopleList(@RequestParam("page") int page, @RequestParam("page_size") int pageSize) {
        validateGetPeopleListRequest(page, pageSize);
        return new ResponseEntity<>(peopleRestService.getPeople(page, pageSize), HttpStatus.OK);
    }

    @GetMapping("/people/frequency")
    public ResponseEntity getPeopleEmailCharacterFrequencyCount() {
        Map<String, Integer> frequencyMap = peopleRestService.getPeopleEmailCharacterFrequencyCount();
        return new ResponseEntity(frequencyMap, HttpStatus.OK);
    }

    @GetMapping("/people/duplicates")
    public ResponseEntity getPossiblePeopleDuplicates() {
        List<PersonDuplicate> possibleDuplicates = peopleRestService.getPossibleDuplicates();
        return new ResponseEntity(possibleDuplicates, HttpStatus.OK);
    }

    private void validateGetPeopleListRequest(int page, int pageSize) {
        if(pageSize < PAGE_SIZE_MIN || pageSize > PAGE_SIZE_MAX) {
            throw new PeopleListArgumentException("Invalid page size provided");
        }

        if(page < PAGE_NUMBER_MIN) {
            throw new PeopleListArgumentException("Invalid page number provided");
        }
    }
}
