package io.shanoon.devtirospringjpaapplication.repository;

import io.shanoon.devtirospringjpaapplication.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Iterable<Author> ageLessThan(int age);

    @Query("SELECT a FROM Author a WHERE a.age > ?1")
    Iterable<Author> authorWithAgeGreaterThan(int age);
}
