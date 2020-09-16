package com.curry.sldemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailCharacterFrequency {

    @JsonProperty
    String emailCharacter;

    @JsonProperty
    int frequency;

    public EmailCharacterFrequency(String emailCharacter, int frequency) {
        this.emailCharacter = emailCharacter;
        this.frequency = frequency;
    }

    public String getEmailCharacter() {
        return this.emailCharacter;
    }

    public int getFrequency() {
        return frequency;
    }
}
