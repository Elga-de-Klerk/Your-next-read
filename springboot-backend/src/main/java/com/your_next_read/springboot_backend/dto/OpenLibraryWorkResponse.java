package com.your_next_read.springboot_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryWorkResponse {
    public Object description;
}