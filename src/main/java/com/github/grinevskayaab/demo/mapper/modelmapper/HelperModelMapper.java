package com.github.grinevskayaab.demo.mapper.modelmapper;

import com.github.grinevskayaab.demo.dto.AlbumFullDto;
import com.github.grinevskayaab.demo.dto.AuthorFullDto;
import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.dto.SongFullDto;
import com.github.grinevskayaab.demo.entity.*;
import lombok.Getter;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Getter
@Component
public class HelperModelMapper {
    private final ModelMapper modelMapper;

    public HelperModelMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Album.class, AlbumFullDto.class)
                .addMappings(
                        mapper -> mapper.using(getAuthorAlbumToAuthorSimpleDto()).map(Album::getAuthorAlbums, AlbumFullDto::setAuthors)
                );

        modelMapper.typeMap(Song.class, SongFullDto.class)
                .addMappings(
                        mapper -> mapper.using(getAuthorSongToAuthorSimpleDto()).map(Song::getAuthorSongs, SongFullDto::setAuthors)
                );

        modelMapper.createTypeMap(Author.class, AuthorFullDto.class)
                .addMappings(
                        mapper -> mapper.using(getAuthorAlbumToAuthorSimpleDto()).map(Author::getAuthorAlbums, AuthorFullDto::setAuthors)
                );

        modelMapper.typeMap(Author.class, AuthorFullDto.class)
                .addMappings(
                        mapper -> mapper.using(getAuthorSongToAuthorSimpleDto()).map(Author::getAuthorSongs, AuthorFullDto::setAuthors)
                );
    }

    public Converter<List<AuthorAlbum>, List<AuthorSimpleDto>> getAuthorAlbumToAuthorSimpleDto() {
          Converter<List<AuthorAlbum>, List<AuthorSimpleDto>> authorAlbumToAuthorSimpleDto =
                new AbstractConverter<>() {
                    @Override
                    protected List<AuthorSimpleDto> convert(List<AuthorAlbum> authorAlbums) {
                        return authorAlbums.stream().map(el -> modelMapper.map(el.getAuthor(), AuthorSimpleDto.class)).toList();
                    }
                };
        return authorAlbumToAuthorSimpleDto;
    }

    public Converter<List<AuthorSong>, List<AuthorSimpleDto>> getAuthorSongToAuthorSimpleDto() {
        Converter<List<AuthorSong>, List<AuthorSimpleDto>> authorSongToAuthorSimpleDto =
                new AbstractConverter<>() {
                    @Override
                    protected List<AuthorSimpleDto> convert(List<AuthorSong> authorSongs) {
                        return authorSongs.stream().map(el -> modelMapper.map(el.getAuthor(), AuthorSimpleDto.class)).toList();
                    }
                };
        return authorSongToAuthorSimpleDto;
    }


}
