package com.github.grinevskayaab.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @Column
    @SequenceGenerator(name="songs_id_seq", sequenceName = "songs_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "songs_id_seq")
    private Long id;

    @Column
    private Integer year = null;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="album_id")
    private Album album;


    @ManyToMany(mappedBy = "songs", fetch = FetchType.LAZY)
    private List<Author> authors;
}
