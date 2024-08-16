package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.SongFullDto;
import com.github.grinevskayaab.demo.dto.SongSimpleDto;
import com.github.grinevskayaab.demo.entity.Song;

import java.util.List;


public interface SongMapper {

    SongSimpleDto getSimpleDto(Song song);

    List<SongSimpleDto> getSimpleDto(List<Song> songs);

    SongFullDto getFullDto(Song song);
}
