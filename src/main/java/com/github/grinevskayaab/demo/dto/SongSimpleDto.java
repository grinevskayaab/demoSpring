package com.github.grinevskayaab.demo.dto;


import lombok.Data;

@Data
public class SongSimpleDto {
    private Long id;
    private Integer year;
    private String name;
    private Long albumId;
}
