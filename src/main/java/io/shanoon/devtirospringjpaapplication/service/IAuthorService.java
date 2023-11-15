package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.domain.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    Author save(Author author);

    List<Author> allAuthors();

    Optional<Author> getAuthor(Long authorId);

    boolean isExist(Long authorId);
}
