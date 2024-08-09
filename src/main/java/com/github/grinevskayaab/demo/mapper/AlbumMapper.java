package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.*;
import com.github.grinevskayaab.demo.entity.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = HelperMapper.class)
public interface AlbumMapper {
    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    List<AlbumSimpleDto> getSimpleDto(List<Album> album);

    AlbumSimpleDto getSimpleDto(Album album);

    @Mapping(source = "authorAlbums", target = "authors", qualifiedByName = "getAuthorSimpleDtoFromAuthorAlbum")
    @Mapping(source = "songs", target = "songs", qualifiedByName = "getSongSimpleDtoFromSong")
    AlbumFullDto getFullDto(Album album);
}
