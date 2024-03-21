package com.example.library.service.impl;

import com.example.library.model.Book;
import com.example.library.model.Category;
import com.example.library.model.events.BookCreatedEvent;
import com.example.library.model.exceptions.InvalidAuthorId;
import com.example.library.model.exceptions.InvalidBookIdException;
import com.example.library.model.exceptions.NoAvailableCopies;
import com.example.library.repository.BookRepository;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> listAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new));

    }

    @Override
    @Transactional
    public Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies) {
        Book book=new Book(name,
                category,
                this.authorService.findById(authorId).orElseThrow(InvalidAuthorId::new),
                availableCopies);
        this.bookRepository.save(book);
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));

        return Optional.of(book);

    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = findById(id).orElseThrow(InvalidAuthorId::new);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(this.authorService.findById(authorId).orElseThrow(InvalidAuthorId::new));
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = findById(id).orElseThrow(InvalidAuthorId::new);
        this.bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    public void bookCreated() {
        System.out.println("Book created");
    }

    @Override
    public Optional<Book> lowerAvailableCopies(Long id) throws NoAvailableCopies {
        Book book = findById(id).orElseThrow(InvalidAuthorId::new);
        if (book.getAvailableCopies() == 0){
            throw new NoAvailableCopies();
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return Optional.of(this.bookRepository.save(book));
    }
}
