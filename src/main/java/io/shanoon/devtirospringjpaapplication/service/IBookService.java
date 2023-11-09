package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.domain.Book;

public interface IBookService {
    public Book createBook(String isbn, Book book);
}
