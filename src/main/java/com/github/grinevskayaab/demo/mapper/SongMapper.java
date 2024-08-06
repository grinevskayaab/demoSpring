package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.SongSimpleDto;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);
    @Mapping(source = "album.id", target="albumId")
    @Mapping(source = "authors", target="authorIds", qualifiedByName = "getAuthorId")
    SongSimpleDto getSimpleDto(Song song);

    @Mapping(source = "album.id", target="albumId")
    @Mapping(source = "authors", target="authorIds", qualifiedByName = "getAuthorId")
    List<SongSimpleDto> getSimpleDto(List<Song> song);

    @Named("getAuthorId")
    default Integer getAuthorId(Author author) {
        return author.getId();
    }
}
