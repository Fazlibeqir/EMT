package com.example.library.web;

import com.example.library.model.Book;
import com.example.library.model.dto.BookDto;
import com.example.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("")
    public List<Book> getAllBooks()
    {
        return bookService.listAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
        return bookService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto)
    {
        return bookService.create(bookDto.getName(), bookDto.getCategory(), bookDto.getAuthorId(), bookDto.getAvailableCopies())
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id)
    {
        return bookService.delete(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto)
    {
        return bookService.update(id, bookDto.getName(), bookDto.getCategory(), bookDto.getAuthorId(), bookDto.getAvailableCopies())
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/getcopy/{id}")
    public ResponseEntity<Void> getCopy(@PathVariable Long id){
        this.bookService.getCopy(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addcopy/{id}")
    public ResponseEntity<Void> addCopy(@PathVariable Long id){
        this.bookService.returnCopy(id);
        return ResponseEntity.ok().build();
    }
}
