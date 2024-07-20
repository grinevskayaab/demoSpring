package com.github.grinevskayaab.demo.controllers;

import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongsController {

    private final SongRepository songRepository;

    @Autowired
    public SongsController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GetMapping()
    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    @GetMapping("/{id}")
    public Song getSong(@PathVariable("id") Long id) {
        return songRepository.findById(id);
    }

    @GetMapping("/top/singles")
    public List<Song> getTopSongs() {
        return songRepository.getTopSingles();
    }

    @PostMapping()
    public Song createSong(@ModelAttribute("song") Song song) {
        return songRepository.create(song);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable("id") Long id) {
        songRepository.delete(id);
    }

    @PatchMapping("/{id}")
    public Song updateSong(@PathVariable("id") Long id, @ModelAttribute("song") Song song) {
        Song newSong = songRepository.findById(id);
        if(song.getName() != null) newSong.setName(song.getName());
        if(song.getYear() != null) newSong.setYear(song.getYear());

        return songRepository.update(newSong);
    }
}
