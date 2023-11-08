package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.domain.Author;
import io.shanoon.devtirospringjpaapplication.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService{
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
}
