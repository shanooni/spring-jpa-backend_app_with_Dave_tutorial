package io.shanoon.devtirospringjpaapplication.controller;

import io.shanoon.devtirospringjpaapplication.DTO.BookDTO;
import io.shanoon.devtirospringjpaapplication.domain.Book;
import io.shanoon.devtirospringjpaapplication.mapper.Mapper;
import io.shanoon.devtirospringjpaapplication.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/v1")
@RestController
public class BookController {

    private Mapper<Book,BookDTO> mapper;
    private BookService bookService;

    public BookController(Mapper<Book, BookDTO> mapper, BookService bookService) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDTO bookDTO){
        Book createdBook = mapper.mapFrom(bookDTO);
        Book savedBook = bookService.createBook(isbn, createdBook);
        BookDTO bookSaved = mapper.mapTo(savedBook);
        return new ResponseEntity<>(bookSaved, HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(
                bookList.stream().map(
                        book -> mapper.mapTo(book)
                ).collect(Collectors.toList()),HttpStatus.OK);
    }
}
