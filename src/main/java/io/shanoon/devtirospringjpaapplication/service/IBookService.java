package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    public Book createUpdateBook(String isbn, Book book);

    List<Book> getAllBooks();

    Page<Book> getAllBooks(Pageable pageable);
    Optional<Book> getBook(String isbn);

    boolean isExist(String isbn);

    Book partialUpdate(String isbn, Book book);

    void delete(String isbn);
}
