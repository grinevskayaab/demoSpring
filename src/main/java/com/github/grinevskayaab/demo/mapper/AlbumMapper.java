package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.AlbumFullDto;
import com.github.grinevskayaab.demo.dto.AlbumSimpleDto;
import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.dto.SongSimpleDto;
import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.Song;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlbumMapper {
    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    @Mapping(target = "songIds", source = "songs", qualifiedByName = "getSongIds")
    @Mapping(target = "authorIds", source = "authors", qualifiedByName = "getAuthorIds")
    List<AlbumSimpleDto> getSimpleDto(List<Album> album);

    @Mapping(target = "songIds", source = "songs", qualifiedByName = "getSongIds")
    @Mapping(target = "authorIds", source = "authors", qualifiedByName = "getAuthorIds")
    AlbumSimpleDto getSimpleDto(Album album);

    AlbumFullDto getFullDto(Album album);

    @Named("getSongIds")
    default Long getSongIds(Song song) {
        return song.getId();
    }

    @Named("getAuthorIds")
    default Integer getAuthorIds(Author author) {
        return author.getId();
    }

//    @InheritConfiguration
//    default SongSimpleDto getSongSimpleDto(Song song) {
//        return Mappers.getMapper(SongMapper.class).getSimpleDto(song);
//    }
//
//    @InheritConfiguration
//    default AuthorSimpleDto getAuthorSimpleDto(Author author) {
//        return Mappers.getMapper(AuthorMapper.class).getSimpleDto(author);
//    }
}
