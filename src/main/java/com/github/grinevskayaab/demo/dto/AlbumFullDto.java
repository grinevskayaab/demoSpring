package com.github.grinevskayaab.demo.dto;

import java.util.List;

public record AlbumFullDto(Long id,
                           String name,
                           Integer year,
                           List<AuthorSimpleDto> authors,
                           List<SongSimpleDto> songs) {
}
