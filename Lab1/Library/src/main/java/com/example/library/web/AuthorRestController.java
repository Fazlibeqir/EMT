package com.example.library.web;

import com.example.library.model.Author;
import com.example.library.model.dto.AuthorDto;
import com.example.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("")
    public List<Author> getAllAuthors()
    {
        return authorService.listAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id)
    {
        return authorService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto authorDto)
    {
        return authorService.create(authorDto.getName(), authorDto.getSurname(), authorDto.getCountryId())
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id)
    {
        return authorService.delete(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto)
    {
        return authorService.update(id, authorDto.getName(), authorDto.getSurname(), authorDto.getCountryId())
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
