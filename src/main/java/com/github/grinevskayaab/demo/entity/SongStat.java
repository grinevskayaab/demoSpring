package com.github.grinevskayaab.demo.entity;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "songs_stat")
public class SongStat {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    @JsonIgnore
    @Id
    private Song song;

    @Column(name = "date")
    @Id
    private Date date;

    @Column(name = "count_plays")
    private Long countPlays;

    @JsonGetter("song_id")
    public Long getSongId() {
        return song.getId();
    }
}
