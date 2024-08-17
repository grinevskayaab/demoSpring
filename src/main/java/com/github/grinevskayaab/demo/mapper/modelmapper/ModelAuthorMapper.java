package com.github.grinevskayaab.demo.mapper.modelmapper;

import com.github.grinevskayaab.demo.dto.*;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.AuthorAlbum;
import com.github.grinevskayaab.demo.entity.AuthorSong;
import com.github.grinevskayaab.demo.mapper.AuthorMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class ModelAuthorMapper implements AuthorMapper {

    private final ModelMapper modelMapper;

    public ModelAuthorMapper() {
        this.modelMapper = new ModelMapper();

        Converter<List<AuthorSong>, List<SongSimpleDto>> authorSongToAuthorSimpleDto =
                new AbstractConverter<>() {
                    @Override
                    protected List<SongSimpleDto> convert(List<AuthorSong> authorSongs) {
                        return authorSongs.stream().map(el -> modelMapper.map(el.getSong(), SongSimpleDto.class)).toList();
                    }
                };

        Converter<List<AuthorAlbum>, List<AlbumSimpleDto>> authorAlbumToAuthorSimpleDto =
                new AbstractConverter<>() {
                    @Override
                    protected List<AlbumSimpleDto> convert(List<AuthorAlbum> authorAlbums) {
                        return authorAlbums.stream().map(el -> modelMapper.map(el.getAlbum(), AlbumSimpleDto.class)).toList();
                    }
                };


        modelMapper.typeMap(Author.class, AuthorFullDto.class)
                .addMappings(
                        mapper -> mapper.using(authorSongToAuthorSimpleDto).map(Author::getAuthorSongs, AuthorFullDto::setSongs)
                ).addMappings(
                        mapper -> mapper.using(authorAlbumToAuthorSimpleDto).map(Author::getAuthorAlbums, AuthorFullDto::setAlbums)
                );
    }

    @Override
    public AuthorSimpleDto getSimpleDto(Author author) {
        return modelMapper.map(author, AuthorSimpleDto.class);
    }

    @Override
    public List<AuthorSimpleDto> getSimpleDto(List<Author> authors) {
        return authors.stream().map(author -> modelMapper.map(author, AuthorSimpleDto.class)).toList();
    }

    @Override
    public AuthorFullDto getFullDto(Author author) {
        return modelMapper.map(author, AuthorFullDto.class);
    }
}
