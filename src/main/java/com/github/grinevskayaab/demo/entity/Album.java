package com.github.grinevskayaab.demo.entity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Album {
    private Long id;
    private String name;
    private Integer year = null;
    private List<Song> songs;

    public Album(Long id) {
        this.id = id;
    }
}

