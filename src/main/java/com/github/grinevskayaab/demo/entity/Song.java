package com.github.grinevskayaab.demo.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.grinevskayaab.demo.repository.SongRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "songs")
@EnableJdbcRepositories(basePackageClasses = SongRepository.class)
public class Song {
    @Id
    @Column(name = "id",insertable=false, updatable=false)
    @SequenceGenerator(name="songs_id_seq", sequenceName = "songs_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "songs_id_seq")
    private Long id;

    @Column
    private Integer year = null;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="album_id")
//    @JoinColumn(name="id")
    private Album album;

    @JsonGetter("album_id")
    public Long getAlbumId() {
        return (album != null) ? album.getId() : null;
    }

}
