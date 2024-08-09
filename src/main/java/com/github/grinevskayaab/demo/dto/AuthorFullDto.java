package com.github.grinevskayaab.demo.dto;

import java.util.List;

public record AuthorFullDto(Integer id, String name, List<AlbumSimpleDto> albums, List<SongSimpleDto> songs){
}
