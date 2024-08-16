package com.github.grinevskayaab.demo.mapper.mapstruct;

import com.github.grinevskayaab.demo.dto.SongFullDto;
import com.github.grinevskayaab.demo.dto.SongSimpleDto;
import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.mapper.SongMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses= HelperMapper.class)
public interface MapstructSongMapper extends SongMapper {
    MapstructSongMapper INSTANCE = Mappers.getMapper(MapstructSongMapper.class);

    @Mapping(source = "album.id", target = "albumId")
    SongSimpleDto getSimpleDto(Song song);

    @Mapping(source = "album.id", target = "albumId")
    List<SongSimpleDto> getSimpleDto(List<Song> songs);

    @Mapping(source = "authorSongs", target = "authors", qualifiedByName = "getAuthorSimpleDtoFromAuthorSong")
    @Mapping(source = "album", target = "album", qualifiedByName = "getAlbumSimpleDtoFromAlbum")
    SongFullDto getFullDto(Song song);
}
