package com.curry.sldemo.service;

import com.curry.sldemo.model.PeopleResponseModel;

import java.util.List;

public interface PeopleService {

    PeopleResponseModel getPeople(int page);
}
