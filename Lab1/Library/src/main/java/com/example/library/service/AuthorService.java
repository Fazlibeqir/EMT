package com.example.library.service;

import com.example.library.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAllAuthors();
    Optional<Author> findById(Long id);
    Optional<Author> create(String name, String surname, Long countryId);
    Optional<Author> update(Long id, String name, String surname, Long countryId);
    Optional<Author> delete(Long id);
}
