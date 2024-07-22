package com.github.grinevskayaab.demo.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Song {
    private Long id;
    private Integer year = null;
    private String name;
    @ToString.Exclude
    @JsonIgnore
    private Album album;

    @JsonGetter("album_id")
    public Long getAlbumId() {
        return (album != null) ? album.getId() : null;
    }
}
