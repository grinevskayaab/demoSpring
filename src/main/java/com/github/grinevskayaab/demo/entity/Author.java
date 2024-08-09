package com.github.grinevskayaab.demo.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Column
    @SequenceGenerator(name = "authors_id_seq", sequenceName = "authors_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_is_seq")
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<AuthorSong> authorSongs;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<AuthorAlbum> authorAlbums;
}
