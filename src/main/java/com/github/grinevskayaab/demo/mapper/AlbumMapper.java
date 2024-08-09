package com.github.grinevskayaab.demo.mapper;

import com.github.grinevskayaab.demo.dto.AlbumFullDto;
import com.github.grinevskayaab.demo.dto.AlbumSimpleDto;
import com.github.grinevskayaab.demo.entity.Album;

import java.util.List;

public interface AlbumMapper {
    List<AlbumSimpleDto> getSimpleDto(List<Album> album);
    AlbumSimpleDto getSimpleDto(Album album);
    AlbumFullDto getFullDto(Album album);
}
