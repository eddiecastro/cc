package com.curry.sldemo.model.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataResponseModel {

    @JsonProperty("paging")
    private PagingMetadataResponseModel paging;

    public PagingMetadataResponseModel getPaging() {
        return this.paging;
    }
}