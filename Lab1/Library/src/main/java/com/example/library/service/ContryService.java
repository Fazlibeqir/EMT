package com.example.library.service;

import com.example.library.model.Country;

import java.util.List;
import java.util.Optional;

public interface ContryService {
    List<Country> listAllContries();
    Optional<Country> findById(Long id);
    Optional<Country> create(String name, String continent);
    Optional<Country> update(Long id, String name, String continent);
    Optional<Country> delete(Long id);
}
