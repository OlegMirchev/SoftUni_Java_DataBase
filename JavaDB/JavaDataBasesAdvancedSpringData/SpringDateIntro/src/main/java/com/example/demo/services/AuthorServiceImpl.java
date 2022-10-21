package com.example.demo.services;

import com.example.demo.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long countId = this.authorRepository.count();

        int randomId = new Random().nextInt((int) countId) + 1;

        return this.authorRepository.findById(randomId).get();
    }

    @Override
    public List<Author> selectAuthorsGetBooks() {
        LocalDate localDate = LocalDate.of(1990, 1, 1);

     return this.authorRepository.findDistinctByBooksReleaseDateBefore(localDate);

    }

    @Override
    public List<Author> selectAuthorsCountBooks() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author selectAllBooksAtAuthorGP() {
        return this.authorRepository.findByLastNameAndFirstName("Powell", "George");

    }
}
