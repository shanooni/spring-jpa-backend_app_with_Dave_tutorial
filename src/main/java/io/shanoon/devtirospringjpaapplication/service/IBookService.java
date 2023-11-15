package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.domain.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    public Book createUpdateBook(String isbn, Book book);

    List<Book> getAllBooks();

    Optional<Book> getBook(String isbn);

    boolean isExist(String isbn);

    Book partialUpdate(String isbn, Book book);
}
