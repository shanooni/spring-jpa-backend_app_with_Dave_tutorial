package io.shanoon.devtirospringjpaapplication.repository;

import io.shanoon.devtirospringjpaapplication.Utils.TestUtils;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {

    private final AuthorRepository underTest;


    @Autowired
    public AuthorRepositoryIntegrationTest(final AuthorRepository authorRepository) {
        this.underTest = authorRepository;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestUtils.testAuthorA();
        underTest.save(author);

        Optional<Author> result = underTest.findById(author.getAuthorId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatManyAuthorsCanBeCreatedAndRecalled(){
        Author authorA = TestUtils.testAuthorA();
        underTest.save(authorA);
        Author authorB = TestUtils.testAuthorB();
        underTest.save(authorB);
        Author authorC = TestUtils.testAuthorC();
        underTest.save(authorC);

        Iterable<Author> results = underTest.findAll();

        assertThat(results)
                .hasSize(3)
                .containsExactly(authorA,authorB,authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated(){
        //create author
        Author testAuthor = TestUtils.testAuthorA();
        underTest.save(testAuthor);
        //update author's age
        testAuthor.setAge(78);
        underTest.save(testAuthor);
        //find author
        Optional<Author> retrieveAuthor = underTest.findById(testAuthor.getAuthorId());

        //assert
        assertThat(retrieveAuthor).isPresent();

        assertThat(retrieveAuthor.get()).isEqualTo(testAuthor);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author testAuthor = TestUtils.testAuthorA();
        underTest.save(testAuthor);

        Optional<Author> retrieveAuthor = underTest.findById(testAuthor.getAuthorId());

        assertThat(retrieveAuthor).isPresent();

        underTest.deleteById(testAuthor.getAuthorId());
        Optional<Author> deletedAuthor = underTest.findById(testAuthor.getAuthorId());

        assertThat(deletedAuthor).isEmpty();
    }



}

