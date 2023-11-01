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
        Author author = TestUtils.testAuthor();
        underTest.save(author);

        Optional<Author> result = underTest.findById(author.getAuthorId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }


}

