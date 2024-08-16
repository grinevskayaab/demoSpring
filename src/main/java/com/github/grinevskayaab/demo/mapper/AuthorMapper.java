package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.AuthorFullDto;
import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.entity.Author;

import java.util.List;

public interface AuthorMapper {
    AuthorSimpleDto getSimpleDto(Author author);

    List<AuthorSimpleDto> getSimpleDto(List<Author> authors);

    AuthorFullDto getFullDto(Author author);
}
