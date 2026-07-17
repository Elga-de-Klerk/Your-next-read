package com.your_next_read.springboot_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibrarySearchResponse {
    public List<Doc> docs;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Doc {
        public String title;
        public List<String> author_name;
        public Integer first_publish_year;
        public Integer number_of_pages_median;
        public List<String> subject;
        public Long cover_i;
        public String key; // e.g. "/works/OL45804W"
    }
}