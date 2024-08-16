package com.github.grinevskayaab.demo.mapper.modelmapper;

import com.github.grinevskayaab.demo.dto.AlbumFullDto;
import com.github.grinevskayaab.demo.dto.AuthorFullDto;
import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.dto.SongFullDto;
import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.AuthorAlbum;
import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
@RequiredArgsConstructor
public class ModelAuthorMapper implements AuthorMapper {
    private final HelperModelMapper helper;

    @Override
    public AuthorSimpleDto getSimpleDto(Author author) {
        return helper.getModelMapper().map(author, AuthorSimpleDto.class);
    }

    @Override
    public List<AuthorSimpleDto> getSimpleDto(List<Author> authors) {
        return authors.stream().map(author -> helper.getModelMapper().map(author, AuthorSimpleDto.class)).toList();
    }

    @Override
    public AuthorFullDto getFullDto(Author author) {
        return helper.getModelMapper().map(author, AuthorFullDto.class);
    }
}
