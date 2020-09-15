package com.curry.sldemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDuplicate {

    @JsonProperty
    Person duplicate1;

    @JsonProperty
    Person duplicate2;

    public PersonDuplicate(Person duplicate1, Person duplicate2) {
        this.duplicate1 = duplicate1;
        this.duplicate2 = duplicate2;
    }
}
