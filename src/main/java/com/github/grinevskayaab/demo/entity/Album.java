package com.github.grinevskayaab.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
//
//    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
//    private List<Song> songs;
//
//    @ManyToMany(mappedBy = "albums", fetch = FetchType.LAZY)
//    private List<Author> authors;
}

