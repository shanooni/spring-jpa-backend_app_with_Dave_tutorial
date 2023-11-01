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
        Author testAuthorB = TestUtils.testAuthorB();

        Book bookByAuthorA = TestUtils.testBookByAuthorA(testAuthorA);
        underTest.save(bookByAuthorA);
        Book bookByAuthorB = TestUtils.testBookByAuthorB(testAuthorB);
        underTest.save(bookByAuthorB);

        Iterable<Book> results = underTest.findAll();

        assertThat(underTest.findAll())
                .hasSize(2)
                .containsExactly(bookByAuthorA,bookByAuthorB);

    }


}
