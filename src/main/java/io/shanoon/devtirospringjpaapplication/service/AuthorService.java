package io.shanoon.devtirospringjpaapplication.service;

import io.shanoon.devtirospringjpaapplication.domain.Author;
import io.shanoon.devtirospringjpaapplication.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorService{
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> allAuthors() {
        return authorRepository
                .findAll();
    }

    @Override
    public Optional<Author> getAuthor(Long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public boolean isExist(Long authorId) {
        return authorRepository.existsById(authorId);
    }

}
