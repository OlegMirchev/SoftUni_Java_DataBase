package com.example.QueryExercise.service;

import com.example.QueryExercise.model.entity.Book;
import com.example.QueryExercise.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> selectAllTitlesByAgeRestriction(String inputRestriction);

    List<String> selectAllTitlesByEditionType(EditionType gold, int copies);

    List<Book> selectAllTitlesAndPriceByPrice();

    List<String> selectAllTitlesReleaseDateIsNot(int year);

    List<Book> selectAllTitlesReleaseDateBeforeThatInput(String date);

    List<String> selectAllTitlesContainsString(String letter);

    List<Book> selectAllTitlesWrittenAuthorStartingLastNameWith(String letter);

    long selectAllNumberBooks(long number);

    Book selectBookWithTitle(String title);

    int updateAllBooksAfterDateAtIncreaseCopies(String date, int numberCopies);

    int deleteBooksThatCopiesLessThanFromInput(int number);
}
