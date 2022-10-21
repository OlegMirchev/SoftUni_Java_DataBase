package com.example.QueryExercise;

import com.example.QueryExercise.model.entity.Book;
import com.example.QueryExercise.model.entity.EditionType;
import com.example.QueryExercise.service.AuthorService;
import com.example.QueryExercise.service.BookService;
import com.example.QueryExercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();

        Scanner scanner = new Scanner(System.in);

//        01
//        String inputRestriction = scanner.nextLine();
//        printAllBooksByTitleWithAgeRestriction(inputRestriction);

//        02
//        printAllBooksByTitleWithEditionTypeLessThan5000Copies();

//        03
//        printAllBooksByTitleAndPriceLessThan5AndHigherThan40();

//        04
//        int year = Integer.parseInt(scanner.nextLine());
//        printAllBooksByTitleThatNotReleaseDate(year);

//        05
//        String dateFormat = scanner.nextLine();
//        printAllBooksByTitleBeforeReleaseDate(dateFormat);

//        06
//        String letter = scanner.nextLine();
//        printAllAllAuthorsThatFirstNameEndsString(letter);

//        07
//        String letter = scanner.nextLine();
//        printAllBooksByTitleThatContainsString(letter);

//        08
//        String letter = scanner.nextLine();
//        printAllBooksByTitleThatWrittenFromAuthorWithLastName(letter);

//        09
//        long number = Integer.parseInt(scanner.nextLine());
//        printAllNumberBooksByTitleThatLengthTitleIsGreaterFromInput(number);

//        10
//        printAllTotalNumberBooksCopiesByAuthor();

//        11
//        String title = scanner.nextLine();
//        printBookInformationWithThatTitleInput(title);

//        12
//        String date = scanner.nextLine();
//        int numberCopies = Integer.parseInt(scanner.nextLine());
//        printTotalCopiesAtAllBooksReleaseAfterInputDate(date, numberCopies);

//        13
//        int number = Integer.parseInt(scanner.nextLine());
//        printNumberBooksDeleteThatCopiesLessThanFromInput(number);

//
//        printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//        printAllAuthorsAndNumberOfTheirBooks();
//        printAllBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void printNumberBooksDeleteThatCopiesLessThanFromInput(int number) {
        int deleteBooks = this.bookService.deleteBooksThatCopiesLessThanFromInput(number);
        System.out.printf("Books were deleted: %d%n", deleteBooks);
    }

    private void printTotalCopiesAtAllBooksReleaseAfterInputDate(String date, int numberCopies) {
        int updateCountBooks = this.bookService.updateAllBooksAfterDateAtIncreaseCopies(date, numberCopies);
        System.out.printf("%d books are released after %s, so total of %d book copies were added%n",
                 updateCountBooks, date, updateCountBooks * numberCopies);
    }

    private void printBookInformationWithThatTitleInput(String title) {
        Book book = this.bookService.selectBookWithTitle(title);
        System.out.println(book.getTitle() + " " + book.getEditionType() + " " + book.getAgeRestriction() + " " + book.getPrice());
    }

    private void printAllTotalNumberBooksCopiesByAuthor() {
        this.authorService.selectTotalNumberBooksByAuthor()
                .forEach(a -> System.out.printf("%s %s - %d%n", a.getFirstName(), a.getLastName(), a.getTotalCopies()));
    }

    private void printAllNumberBooksByTitleThatLengthTitleIsGreaterFromInput(long number) {
        long countBooks = this.bookService.selectAllNumberBooks(number);
        System.out.printf("There are %d books with longer title than %d symbols%n", countBooks, number);
    }

    private void printAllBooksByTitleThatWrittenFromAuthorWithLastName(String letter) {
        this.bookService.selectAllTitlesWrittenAuthorStartingLastNameWith(letter)
                .forEach(b -> System.out.printf("%s (%s %s)%n", b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()));
    }

    private void printAllBooksByTitleThatContainsString(String letter) {
        this.bookService.selectAllTitlesContainsString(letter)
                .forEach(System.out::println);
    }

    private void printAllAllAuthorsThatFirstNameEndsString(String letter) {
        this.authorService.selectAllAuthorsThatFirstNameEnds(letter)
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void printAllBooksByTitleBeforeReleaseDate(String date) {
        this.bookService.selectAllTitlesReleaseDateBeforeThatInput(date)
                .forEach(b -> System.out.println(b.getTitle() + " " + b.getEditionType() + " " + b.getPrice()));
    }

    private void printAllBooksByTitleThatNotReleaseDate(int year) {
        this.bookService.selectAllTitlesReleaseDateIsNot(year)
                .forEach(System.out::println);
    }

    private void printAllBooksByTitleAndPriceLessThan5AndHigherThan40() {
        this.bookService.selectAllTitlesAndPriceByPrice()
                .forEach(b -> System.out.println(b.getTitle() + " - " + b.getPrice()));
    }

    private void printAllBooksByTitleWithEditionTypeLessThan5000Copies() {
        this.bookService.selectAllTitlesByEditionType(EditionType.GOLD, 5000).forEach(System.out::println);
    }

    private void printAllBooksByTitleWithAgeRestriction(String inputRestriction) {
        this.bookService.selectAllTitlesByAgeRestriction(inputRestriction).forEach(System.out::println);
    }

    private void printAllBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
