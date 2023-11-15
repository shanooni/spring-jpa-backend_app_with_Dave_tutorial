package io.shanoon.devtirospringjpaapplication.controller;


import io.shanoon.devtirospringjpaapplication.DTO.AuthorDTO;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import io.shanoon.devtirospringjpaapplication.mapper.Mapper;
import io.shanoon.devtirospringjpaapplication.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Author savedAuthor = authorService.save(createdAuthor);
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

    @GetMapping(path = "/authors/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("authorId") Long authorId){
        Optional<Author> foundAuthor = authorService.getAuthor(authorId);
        return foundAuthor.map(
                author -> {
                    AuthorDTO authorDTO = authorAuthorDTOMapper.mapTo(author);
                    return new ResponseEntity<>(authorDTO, HttpStatus.OK);
                }
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable("authorId") Long authorId,
                                                  @RequestBody AuthorDTO authorDTO){

        if(!authorService.isExist(authorId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            authorDTO.setAuthorId(authorId);
            Author author = authorAuthorDTOMapper.mapFrom(authorDTO);
            Author updatedAuthor =  authorService.save(author);
            return new ResponseEntity<>(
                    authorAuthorDTOMapper.mapTo(updatedAuthor),
                    HttpStatus.OK);

    }
}
