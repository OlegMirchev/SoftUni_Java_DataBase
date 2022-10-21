package com.example.demo;

import com.example.demo.entities.Book;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookServices;
import com.example.demo.services.SeedServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedServices seedServices;
    private final BookServices bookServices;
    private final AuthorService authorService;

    @Autowired
    public ConsoleRunner(SeedServices seedServices, BookServices bookServices, AuthorService authorService) {
        this.seedServices = seedServices;
        this.bookServices = bookServices;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

// 01 BooksAfter2000Year
// this.bookServices.selectBooksTitle().stream().map(Book::getTitle).forEach(System.out::println);

// 02 AuthorsBooksBefore1990Year
// this.authorService.selectAuthorsGetBooks().forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));

// 03 GetAllAuthorsAndTheirBooks
// List<Author> authors = this.authorService.selectAuthorsCountBooks();
// authors.stream().sorted((a, b) -> b.getBooks().size() - a.getBooks().size())
//.forEach(a -> System.out.printf("%s %s %d%n", a.getFirstName(), a.getLastName(), a.getBooks().size()))

// 04 GetAllBooksAtAuthorGeorgePowell
// Set<Book> books = this.authorService.selectAllBooksAtAuthorGP().getBooks();
// books.stream().sorted((a, b) -> b.getReleaseDate().compareTo(a.getReleaseDate()))
//               .sorted(Comparator.comparing(Book::getTitle))
//               .forEach(b -> System.out.printf("%s %s %d%n", b.getTitle(), b.getReleaseDate(), b.getCopies()));

    }
}
