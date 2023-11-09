package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.DTO.AuthorDTO;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IAuthorService {
    Author createAuthor(Author author);

    List<Author> allAuthors();
}
