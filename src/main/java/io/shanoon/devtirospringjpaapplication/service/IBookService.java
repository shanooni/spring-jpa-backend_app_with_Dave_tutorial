package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.domain.Book;

import java.util.List;

public interface IBookService {
    public Book createBook(String isbn, Book book);

    List<Book> getAllBooks();
}
