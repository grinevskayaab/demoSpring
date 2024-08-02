package com.github.grinevskayaab.demo.service;

import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public List<Song> getSongs() {
        List<Song> song = songRepository.findAll();
        for (Song song1 : song) {
            System.out.println(song1.getAlbum().getName());
        }
        return song;
    }

    public Song getSong(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    public Song updateSong(Long id, Song song) {
        Optional<Song> newSong = songRepository.findById(id);
        if (newSong.isPresent()) {
            Song updatedSong = newSong.get();
            if (song.getName() != null) updatedSong.setName(song.getName());
            if (song.getYear() != null) updatedSong.setYear(song.getYear());

            return songRepository.save(updatedSong);
        }
        return null;
    }
}
