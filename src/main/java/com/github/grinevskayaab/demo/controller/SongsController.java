package com.github.grinevskayaab.demo.controller;

import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongsController {

    private final SongService songService;


    @GetMapping()
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/{id}")
    public Song getSong(@PathVariable("id") Long id) {
        return songService.getSong(id);
    }


    @PostMapping()
    public Song createSong(@ModelAttribute("song") Song song) {
        return songService.createSong(song);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable("id") Long id) {
        songService.deleteSong(id);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable("id") Long id, @ModelAttribute("song") Song song) {
        return songService.updateSong(id, song);
    }
}
