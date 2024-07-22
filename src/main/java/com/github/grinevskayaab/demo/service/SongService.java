package com.github.grinevskayaab.demo.service;

import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public Song getSong(Long id) {
        return songRepository.findById(id);
    }

    public List<Song> getTopSongs() {
        return songRepository.getTopSingles();
    }

    public Song createSong(Song song) {
        return songRepository.create(song);
    }

    public void deleteSong(Long id) {
        songRepository.delete(id);
    }

    public Song updateSong(Long id, Song song) {
        Song newSong = songRepository.findById(id);
        if (song.getName() != null) newSong.setName(song.getName());
        if (song.getYear() != null) newSong.setYear(song.getYear());

        return songRepository.update(newSong);
    }
}
