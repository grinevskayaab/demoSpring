package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.AuthorFullDto;
import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses= HelperMapper.class)
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorSimpleDto getSimpleDto(Author author);

    List<AuthorSimpleDto> getSimpleDto(List<Author> author);

    @Mapping(source = "authorSongs", target = "songs", qualifiedByName = "getSongSimpleDtoFromAuthorSong")
    @Mapping(source = "authorAlbums", target = "albums", qualifiedByName = "getAlbumSimpleDtoFromAuthorAlbum")
    AuthorFullDto getFullDto(Author author);
}
