package io.shanoon.devtirospringjpaapplication.controller;

import io.shanoon.devtirospringjpaapplication.DTO.BookDTO;
import io.shanoon.devtirospringjpaapplication.domain.Book;
import io.shanoon.devtirospringjpaapplication.mapper.Mapper;
import io.shanoon.devtirospringjpaapplication.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> createUpdatedBook(@PathVariable("isbn") String isbn,
                                                     @RequestBody BookDTO bookDTO){
        Book book = mapper.mapFrom(bookDTO);
        boolean booksExist = bookService.isExist(isbn);
        Book saveBook = bookService.createUpdateBook(isbn, book);
        BookDTO savedBookDto = mapper.mapTo(saveBook);

        if(booksExist){
            return new ResponseEntity<>(savedBookDto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(savedBookDto,HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(
                bookList.stream().map(
                        book -> mapper.mapTo(book)
                ).collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("isbn") String isbn){
        Optional<Book> serviceBook =  bookService.getBook(isbn);
        return serviceBook.map(
                book -> {
                    BookDTO bookDTO = mapper.mapTo(book);
                    return new ResponseEntity<>(bookDTO,HttpStatus.OK);
                }
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> partialUpdate(@PathVariable("isbn") String isbn, BookDTO bookDTO){
        Book book = mapper.mapFrom(bookDTO);
        boolean bookExist = bookService.isExist(isbn);
        if(!bookExist){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book updateBook = bookService.partialUpdate(isbn,book);
        return new ResponseEntity<>(mapper.mapTo(updateBook),HttpStatus.OK);

    }
}
