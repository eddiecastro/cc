package com.curry.sldemo.service;

import com.curry.sldemo.model.PeopleResponseModel;

public interface PeopleService {

    PeopleResponseModel getPeople(int requestedPage, int pageSize);
}
