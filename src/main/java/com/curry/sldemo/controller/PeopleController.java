package com.curry.sldemo.controller;

import com.curry.sldemo.model.PeopleResponseModel;
import com.curry.sldemo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

    private static final int PAGE_SIZE_MIN = 1;
    private static final int PAGE_SIZE_MAX = 100;
    private static final int PAGE_NUMBER_MIN = 1;

    private PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/people")
    public ResponseEntity<PeopleResponseModel> getPeopleList(@RequestParam("page") int page, @RequestParam("page_size") int pageSize) {

        try {
            validateGetPeopleListRequest(page, pageSize);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(peopleService.getPeople(page, pageSize), HttpStatus.OK);
    }

    private void validateGetPeopleListRequest(int page, int pageSize) {
        if(pageSize < PAGE_SIZE_MIN || pageSize > PAGE_SIZE_MAX) {
            //TODO: what do we show to the user
            throw new IllegalArgumentException();
        }

        if(page < PAGE_NUMBER_MIN) {
            //TODO: what do we show to the user
            //NOTE: the SL API will just default to page 1 if you give it a page number that doesn't exist for this set of people. Do we still check?
            throw new IllegalArgumentException();
        }
    }
}
