package io.shanoon.devtirospringjpaapplication.mapper;

import io.shanoon.devtirospringjpaapplication.DTO.AuthorDTO;
import io.shanoon.devtirospringjpaapplication.domain.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper< Author,AuthorDTO>{

    private ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Author mapFrom(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }

    @Override
    public AuthorDTO mapTo(Author author) {
        return modelMapper.map(author, AuthorDTO.class);
    }
}
