package com.curry.sldemo.controller;

import com.curry.sldemo.model.PeopleResponseModel;
import com.curry.sldemo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

    private PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/people")
    public PeopleResponseModel getPeopleList(@RequestParam("page") int page) {
        return peopleService.getPeople(page);
    }
}
