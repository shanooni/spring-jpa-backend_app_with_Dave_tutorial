package io.shanoon.devtirospringjpaapplication.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "authors")
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_id_sequence")
    private Long authorId;
    private String name;
    private int age;
}
