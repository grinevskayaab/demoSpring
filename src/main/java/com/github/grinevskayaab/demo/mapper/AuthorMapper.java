package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    @Mapping(source="songs", target="songIds", qualifiedByName = "getSongId")
    @Mapping(source = "albums", target="albumIds", qualifiedByName = "getAlbumId")
    AuthorSimpleDto getSimpleDto(Author author);

    @Mapping(source="songs", target="songIds", qualifiedByName = "getSongId")
    @Mapping(source = "albums", target="albumIds", qualifiedByName = "getAlbumId")
    List<AuthorSimpleDto> getSimpleDto(List<Author> author);

    @Named("getSongId")
    default Long getSongId(Song song) {
        return song.getId();
    }

    @Named("getAlbumId")
    default Long getAlbumId(Album album) {
        return album.getId();
    }
}
