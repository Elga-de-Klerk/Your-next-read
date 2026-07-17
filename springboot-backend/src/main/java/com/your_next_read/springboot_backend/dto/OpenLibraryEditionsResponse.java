package com.your_next_read.springboot_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryEditionsResponse {
    public List<Entry> entries;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Entry {
        public Integer number_of_pages;
    }
}