package com.github.grinevskayaab.demo.entity;

import com.github.grinevskayaab.demo.repository.AlbumRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "albums")
@EnableJdbcRepositories(basePackageClasses = AlbumRepository.class)
public class Album {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "albums_id_seq", sequenceName = "albums_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albums_id_seq")
    private Long id;

    @Column(name="name", unique=true)
    private String name;

    @Column
    private Integer year = null;

//    @Transient
    @OneToMany(mappedBy = "album")
    private List<Song> songs = new ArrayList<>();

    public Album(Long id) {
        this.id = id;
    }
}

