package com.github.grinevskayaab.demo.controller;

import com.github.grinevskayaab.demo.dto.SongFullDto;
import com.github.grinevskayaab.demo.dto.SongSimpleDto;
import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.mapper.SongMapper;
import com.github.grinevskayaab.demo.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {

    private final SongService songService;
    private final SongMapper songMapper;

    @GetMapping()
    public List<SongSimpleDto> getSongs() {
        return songMapper.getSimpleDto(songService.getSongs());
    }

    @GetMapping("/{id}")
    public SongFullDto getSong(@PathVariable("id") Long id) {
        return songMapper.getFullDto(songService.getSong(id));
    }

    @GetMapping("/name/{name}")
    public SongSimpleDto getSongByName(@PathVariable("name") String name) {
        return songMapper.getSimpleDto(songService.getSongByName(name));
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
