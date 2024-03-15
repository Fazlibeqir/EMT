package com.example.library.model.dto;

import com.example.library.model.Category;
import lombok.Data;

@Data
public class BookDto {
    String name;
    Category category;
    Long authorId;
    Integer availableCopies;
}
