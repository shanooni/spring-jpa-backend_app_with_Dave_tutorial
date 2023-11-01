package io.shanoon.devtirospringjpaapplication.Utils;

import io.shanoon.devtirospringjpaapplication.domain.Author;
import io.shanoon.devtirospringjpaapplication.domain.Book;

public class TestUtils {
    public static Author testAuthor() {
        Author author = Author.builder()
                .authorId(1L)
                .age(37)
                .name("John Doe")
                .build();
        return author;
    }

    public static Book testBook(final Author author) {
        Book book = Book.builder()
                .authorId(author)
                .isbn("134-456-789")
                .title("The Greatest")
                .build();
        return book;
    }
}
