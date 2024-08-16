package com.github.grinevskayaab.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorFullDto{
    private Integer id;
    private String name;
    private List<AlbumSimpleDto> albums;
    private List<SongSimpleDto> songs;
}
