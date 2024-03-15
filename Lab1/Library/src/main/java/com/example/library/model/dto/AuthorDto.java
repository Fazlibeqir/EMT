package com.example.library.model.dto;

import lombok.Data;

@Data
public class AuthorDto {
    String name;
    String surname;
    Long countryId;
}
