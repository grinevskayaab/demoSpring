package com.github.grinevskayaab.demo.mapper.mapstruct;

import com.github.grinevskayaab.demo.dto.*;
import com.github.grinevskayaab.demo.entity.*;
import com.github.grinevskayaab.demo.mapper.AlbumMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = HelperMapper.class)
public interface MapstructAlbumMapper extends AlbumMapper {
    MapstructAlbumMapper INSTANCE = Mappers.getMapper(MapstructAlbumMapper.class);

    @Override
    @Mapping(source = "authorAlbums", target = "authors", qualifiedByName = "getAuthorSimpleDtoFromAuthorAlbum")
    @Mapping(source = "songs", target = "songs", qualifiedByName = "getSongSimpleDtoFromSong")
     AlbumFullDto getFullDto(Album album);
}
