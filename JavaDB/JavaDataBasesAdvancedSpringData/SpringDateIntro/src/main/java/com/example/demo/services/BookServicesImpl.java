package com.example.demo.services;

import com.example.demo.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServicesImpl implements BookServices {

    private BookRepository bookRepository;

    @Autowired
    public BookServicesImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> selectBooksTitle() {
        LocalDate localDate = LocalDate.of(2000, 12, 31);

        return this.bookRepository.findByReleaseDateAfter(localDate);
    }
}
