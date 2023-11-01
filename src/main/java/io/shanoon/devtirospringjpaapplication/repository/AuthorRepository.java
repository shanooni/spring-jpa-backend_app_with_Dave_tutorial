package io.shanoon.devtirospringjpaapplication.repository;

import io.shanoon.devtirospringjpaapplication.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
