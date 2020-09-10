package com.curry.sldemo.model.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingMetadataResponseModel {

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("next_page")
    private int nextPage;

    @JsonProperty("prev_page")
    private int previousPage;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_count")
    private int totalCount;

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
