package com.github.grinevskayaab.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlbumFullDto {
    private Long id;
    private String name;
    private Integer year;
    private List<AuthorSimpleDto> authors;
    private List<SongSimpleDto> songs;
}
