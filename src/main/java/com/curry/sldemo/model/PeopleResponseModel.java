package com.curry.sldemo.model;

import com.curry.sldemo.model.metadata.MetadataResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PeopleResponseModel {

    @JsonProperty("metadata")
    private MetadataResponseModel metadataResponseModel;

    @JsonProperty("data")
    private List<Person> people;

    public MetadataResponseModel getMetadataResponseModel() {
        return metadataResponseModel;
    }

    public List<Person> getPeople() {
        return people;
    }
}
