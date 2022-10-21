package com.example.demo.services;

import com.example.demo.entities.Author;

import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();

    List<Author> selectAuthorsGetBooks();

    List<Author> selectAuthorsCountBooks();

    Author selectAllBooksAtAuthorGP();
}
