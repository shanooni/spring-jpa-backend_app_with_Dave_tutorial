package io.shanoon.devtirospringjpaapplication.mapper;

import io.shanoon.devtirospringjpaapplication.DTO.BookDTO;
import io.shanoon.devtirospringjpaapplication.domain.Book;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookDTO>{

    private ModelMapper mapper;

    public BookMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Book mapFrom(BookDTO bookDTO) {
        return mapper.map(bookDTO, Book.class);
    }

    @Override
    public BookDTO mapTo(Book book) {
        return mapper.map(book, BookDTO.class);
    }
}
