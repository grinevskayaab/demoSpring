package com.github.grinevskayaab.demo.mapper.mapstruct;


import com.github.grinevskayaab.demo.dto.AuthorFullDto;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.mapper.AuthorMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses= HelperMapper.class)
public interface MapstructAuthorMapper extends AuthorMapper {
    MapstructAuthorMapper INSTANCE = Mappers.getMapper(MapstructAuthorMapper.class);

    @Mapping(source = "authorSongs", target = "songs", qualifiedByName = "getSongSimpleDtoFromAuthorSong")
    @Mapping(source = "authorAlbums", target = "albums", qualifiedByName = "getAlbumSimpleDtoFromAuthorAlbum")
    AuthorFullDto getFullDto(Author author);
}
