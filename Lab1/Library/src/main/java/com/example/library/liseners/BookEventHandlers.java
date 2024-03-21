package com.example.library.liseners;

import com.example.library.model.events.BookCreatedEvent;
import com.example.library.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandlers {
    private final BookService bookService;

    public BookEventHandlers(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookCreated(BookCreatedEvent event){
        this.bookService.bookCreated();
    }
}
