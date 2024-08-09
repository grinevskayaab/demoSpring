package com.github.grinevskayaab.demo.dto;

import java.util.List;
import java.util.Set;

public record SongFullDto(Long id,
                          Integer year,
                          String name,
                          AlbumSimpleDto album,
                          List<AuthorSimpleDto> authors) {
}
