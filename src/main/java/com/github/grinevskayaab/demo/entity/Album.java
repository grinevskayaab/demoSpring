package com.github.grinevskayaab.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @Column
    @SequenceGenerator(name = "albums_id_seq", sequenceName = "albums_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albums_id_seq")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer year = null;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private Set<Song> songs;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private Set<AuthorAlbum> authorAlbums;
}

