package com.github.grinevskayaab.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class SongFullDto {
    private Long id;
    private Integer year;
    private String name;
    private AlbumSimpleDto album;
    private List<AuthorSimpleDto> authors;
}
