package com.curry.sldemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    @JsonProperty("display_name")
    String displayName;

    @JsonProperty("email_address")
    String emailAddress;

    @JsonProperty("title")
    String jobTitle;

    public String getEmailAddress() {
        return emailAddress;
    }
}
