package com.example.QueryExercise.repository;

import com.example.QueryExercise.model.entity.AgeRestriction;
import com.example.QueryExercise.model.entity.Book;
import com.example.QueryExercise.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findByAgeRestriction(AgeRestriction inputRestriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType gold, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal price1, BigDecimal price2);

    @Query("SELECT b.title FROM Book AS b WHERE YEAR(b.releaseDate) NOT LIKE :year")
    List<String> findByReleaseDate(int year);

    @Query("SELECT b.title FROM Book AS b WHERE b.title LIKE %:word%")
    List<String> findByTitle(@Param("word") String letter);

    @Query("SELECT b FROM Book AS b" +
            " JOIN b.author AS a" +
            " WHERE a.lastName LIKE :letter%")
    List<Book> findByTitleAndAuthorsFullName(String letter);

    @Query("SELECT COUNT(b) FROM Book AS b WHERE char_length(b.title) > :digit")
    long findAllNumberBooksLengthTitleBiggerFromNumber(@Param("digit") long number);

    Book findByTitleLike(String title);

    @Modifying
    @Transactional
    @Query("UPDATE FROM Book AS b" +
            " SET b.copies = b.copies + :numberCopies" +
            " WHERE b.releaseDate > :localDate")
    int findBooksAndIncreaseCopiesAfterDate(LocalDate localDate, int numberCopies);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book AS b" +
            " WHERE b.copies < :digit")
    int findBooksCopiesLessThanNumber(@Param("digit") int number);
}
