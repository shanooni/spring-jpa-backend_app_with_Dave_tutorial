package io.shanoon.devtirospringjpaapplication.repository;

import io.shanoon.devtirospringjpaapplication.Utils.TestUtils;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import io.shanoon.devtirospringjpaapplication.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
    private final BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTest(final BookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestUtils.testAuthorA();
        Book book = TestUtils.testBookByAuthorA(author);
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatManyBooksCanBeCreatedAndRecalled(){
        Author testAuthorA = TestUtils.testAuthorA();

        Book bookByAuthorA = TestUtils.testBookByAuthorA(testAuthorA);
        underTest.save(bookByAuthorA);
        Book bookTwoByAuthorA = TestUtils.testBookByAuthorB(testAuthorA);
        underTest.save(bookTwoByAuthorA);

        Iterable<Book> results = underTest.findAll();

        assertThat(results)
                .hasSize(2)
                .containsExactly(bookByAuthorA,bookTwoByAuthorA);

    }


    @Test
    public void testThatBookCanBeUpdated(){
        Author testAuthor = TestUtils.testAuthorA();
        Book testBook = TestUtils.testBookByAuthorA(testAuthor);
        underTest.save(testBook);

        testBook.setTitle("In the chest of a woman");
        underTest.save(testBook);

        Optional<Book> retrieveBook = underTest.findById(testBook.getIsbn());

        assertThat(retrieveBook).isPresent();
        assertThat(retrieveBook.get().getTitle()).isEqualTo("In the chest of a woman");
        assertThat(retrieveBook.get().getTitle()).isNotEqualTo("The Greatest");
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author testAuthor = TestUtils.testAuthorA();
        Book testBook = TestUtils.testBookByAuthorA(testAuthor);
        underTest.save(testBook);

        Optional<Book> retrievedBook = underTest.findById(testBook.getIsbn());
        assertThat(retrievedBook).isPresent();

        underTest.deleteById(testBook.getIsbn());
        Optional<Book> deletedBook = underTest.findById(testBook.getIsbn());

        assertThat(deletedBook).isEmpty();

    }
}
