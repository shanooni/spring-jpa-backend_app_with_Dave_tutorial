package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.domain.Book;
import io.shanoon.devtirospringjpaapplication.repository.BookRepository;
import org.springframework.stereotype.Service;


@Service
public class BookService implements IBookService{

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(String isbn, Book book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
