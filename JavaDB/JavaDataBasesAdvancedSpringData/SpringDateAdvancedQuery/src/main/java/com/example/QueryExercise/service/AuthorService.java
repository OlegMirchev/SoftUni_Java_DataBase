package com.example.QueryExercise.service;

import com.example.QueryExercise.model.entity.Author;
import com.example.QueryExercise.model.entity.AuthorWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> selectAllAuthorsThatFirstNameEnds(String letter);

    List<AuthorWithTotalCopies> selectTotalNumberBooksByAuthor();
}
