package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.SongFullDto;
import com.github.grinevskayaab.demo.dto.SongSimpleDto;
import com.github.grinevskayaab.demo.entity.Song;
import org.hibernate.mapping.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses= HelperMapper.class)
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(source = "album.id", target = "albumId")
    SongSimpleDto getSimpleDto(Song song);

    @Mapping(source = "album.id", target = "albumId")
    List<SongSimpleDto> getSimpleDto(List<Song> song);

    @Mapping(source = "authorSongs", target = "authors", qualifiedByName = "getAuthorSimpleDtoFromAuthorSong")
    @Mapping(source = "album", target = "album", qualifiedByName = "getAlbumSimpleDtoFromAlbum")
    SongFullDto getFullDto(Song song);
}
