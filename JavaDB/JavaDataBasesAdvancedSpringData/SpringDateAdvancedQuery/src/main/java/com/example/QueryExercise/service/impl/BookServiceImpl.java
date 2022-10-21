package com.example.QueryExercise.service.impl;

import com.example.QueryExercise.model.entity.*;
import com.example.QueryExercise.repository.BookRepository;
import com.example.QueryExercise.service.AuthorService;
import com.example.QueryExercise.service.BookService;
import com.example.QueryExercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> selectAllTitlesByAgeRestriction(String inputRestriction) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(inputRestriction.toUpperCase());

        return this.bookRepository.findByAgeRestriction(ageRestriction).stream()
                .map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> selectAllTitlesByEditionType(EditionType gold, int copies) {
        return this.bookRepository.findByEditionTypeAndCopiesLessThan(gold, copies)
                .stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<Book> selectAllTitlesAndPriceByPrice() {
        BigDecimal bigDecimal1 = BigDecimal.valueOf(5);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(40);

        return this.bookRepository.findByPriceLessThanOrPriceGreaterThan(bigDecimal1, bigDecimal2);
    }

    @Override
    public List<String> selectAllTitlesReleaseDateIsNot(int year) {
        return this.bookRepository.findByReleaseDate(year);
    }

    @Override
    public List<Book> selectAllTitlesReleaseDateBeforeThatInput(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return this.bookRepository.findAllByReleaseDateBefore(localDate);
    }

    @Override
    public List<String> selectAllTitlesContainsString(String letter) {
        return this.bookRepository.findByTitle(letter);
    }

    @Override
    public List<Book> selectAllTitlesWrittenAuthorStartingLastNameWith(String letter) {
        return this.bookRepository.findByTitleAndAuthorsFullName(letter);
    }

    @Override
    public long selectAllNumberBooks(long number) {
        return this.bookRepository.findAllNumberBooksLengthTitleBiggerFromNumber(number);
    }

    @Override
    public Book selectBookWithTitle(String title) {
        return this.bookRepository.findByTitleLike(title);
    }

    @Override
    public int updateAllBooksAfterDateAtIncreaseCopies(String date, int numberCopies) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return this.bookRepository.findBooksAndIncreaseCopiesAfterDate(localDate, numberCopies);
    }

    @Override
    public int deleteBooksThatCopiesLessThanFromInput(int number) {
        return this.bookRepository.findBooksCopiesLessThanNumber(number);
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
