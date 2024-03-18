package com.example.library.service.impl;

import com.example.library.model.Author;
import com.example.library.model.exceptions.InvalidAuthorId;
import com.example.library.model.exceptions.InvalidCountryId;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import com.example.library.service.ContryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ContryService contryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, ContryService contryService) {
        this.authorRepository = authorRepository;
        this.contryService = contryService;
    }

    @Override
    public List<Author> listAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(this.authorRepository.findById(id).orElseThrow(InvalidAuthorId::new));
    }

    @Override
    public Optional<Author> create(String name, String surname, Long countryId) {
        return Optional.of(authorRepository.save(new Author(name, surname, contryService.findById(countryId).orElseThrow(InvalidCountryId::new))));

    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {
        Author author = findById(id).orElseThrow(InvalidAuthorId::new);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(contryService.findById(countryId).orElseThrow(InvalidCountryId::new));
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> delete(Long id) {
        Author author = findById(id).orElseThrow(InvalidAuthorId::new);
        authorRepository.deleteById(id);
        return Optional.of(author);
    }
}
