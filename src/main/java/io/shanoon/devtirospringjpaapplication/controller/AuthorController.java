package io.shanoon.devtirospringjpaapplication.controller;


import io.shanoon.devtirospringjpaapplication.DTO.AuthorDTO;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import io.shanoon.devtirospringjpaapplication.mapper.AuthorMapper;
import io.shanoon.devtirospringjpaapplication.mapper.Mapper;
import io.shanoon.devtirospringjpaapplication.service.AuthorService;
import io.shanoon.devtirospringjpaapplication.service.IAuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1")
@RestController
public class AuthorController {

    private AuthorService authorService;
    private Mapper<Author, AuthorDTO> authorAuthorDTOMapper;
    public AuthorController(AuthorService authorService, Mapper<Author, AuthorDTO> authorAuthorDTOMapper) {
        this.authorService = authorService;
        this.authorAuthorDTOMapper = authorAuthorDTOMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO author){
        Author createdAuthor = authorAuthorDTOMapper.mapFrom(author);
        Author savedAuthor = authorService.createAuthor(createdAuthor);
        return new ResponseEntity<>(authorAuthorDTOMapper.mapTo(savedAuthor), HttpStatus.CREATED);
    }
    @GetMapping(path = "/authors")
    public List<AuthorDTO> allAuthors(){
        List<Author> authors = authorService.allAuthors();
        return authors.stream().map(
                author ->
                        authorAuthorDTOMapper.mapTo(author)
        ).toList();
    }
}