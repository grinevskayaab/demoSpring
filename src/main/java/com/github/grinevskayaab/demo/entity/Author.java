package com.github.grinevskayaab.demo.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.grinevskayaab.demo.repository.AuthorRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "authors")
@EnableJdbcRepositories(basePackageClasses = AuthorRepository.class)
public class Author {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "authors_id_seq", sequenceName = "authors_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_is_seq")
    private Integer id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "authors_songs",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")}
    )
    @JsonIgnore
    List<Song> songs = new ArrayList<>();

    @JsonGetter(value ="songs_id")
    public String getSongsId() {
        StringBuilder result = new StringBuilder();
        songs.forEach(song -> result.append(song.getId()).append(", "));
        return result.toString();
    }
}
