package io.shanoon.devtirospringjpaapplication.Utils;

import io.shanoon.devtirospringjpaapplication.DTO.AuthorDTO;
import io.shanoon.devtirospringjpaapplication.DTO.BookDTO;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import io.shanoon.devtirospringjpaapplication.domain.Book;

public class TestUtils {
    public static Author testAuthorA() {
        Author author = Author.builder()
                .authorId(1L)
                .age(37)
                .name("John Doe")
                .build();
        return author;
    }

    public static Author testAuthorB() {
        Author author = Author.builder()
                .authorId(2L)
                .age(34)
                .name("Ama Serwaa")
                .build();
        return author;
    }
    public static Author testAuthorC() {
        Author author = Author.builder()
                .authorId(3L)
                .age(45)
                .name("Fred Amo")
                .build();
        return author;
    }

    public static AuthorDTO testAuthorDto() {
        AuthorDTO author = AuthorDTO.builder()
                .id(3L)
                .age(34)
                .name("Fred Amo")
                .build();
        return author;
    }


    public static Book testBookByAuthorA(final Author author) {
        Book book = Book.builder()
                .authorId(author)
                .isbn("134-456-789")
                .title("The Greatest")
                .build();
        return book;
    }

    public static Book testBookByAuthorB(final Author author) {
        Book book = Book.builder()
                .authorId(author)
                .isbn("987-000-765")
                .title("The Happy Palace")
                .build();
        return book;
    }

    public static BookDTO createTestBookDto(AuthorDTO authorDTO) {
        return BookDTO.builder()
                .isbn("234-45-6-00-5")
                .author(authorDTO)
                .title("War and Peace")
                .build();
    }
}
