package com.example.library.model.events;

import com.example.library.model.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;



@Getter
public class BookCreatedEvent extends ApplicationEvent {
    public BookCreatedEvent(Book source) {
        super(source);
    }

}
