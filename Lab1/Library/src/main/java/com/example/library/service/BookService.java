package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAllBooks();
    Optional<Book> findById(Long id);
    Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> delete(Long id);
    void bookCreated();

    void getCopy(Long id);
    void returnCopy(Long id);
   }
