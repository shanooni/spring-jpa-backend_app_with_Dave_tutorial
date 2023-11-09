package io.shanoon.devtirospringjpaapplication.DTO;

import io.shanoon.devtirospringjpaapplication.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDTO {
    private String isbn;
    private String title;
    private AuthorDTO author;
}
