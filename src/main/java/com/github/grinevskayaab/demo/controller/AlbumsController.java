package com.github.grinevskayaab.demo.controller;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumsController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping()
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable("id") Long id) {
        return albumRepository.findById(id);
    }

    @PostMapping()
    public Album createAlbum(@ModelAttribute("album") Album album) {
        return albumRepository.create(album);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable("id") Long id) {
        albumRepository.delete(id);
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable("id") Long id, @ModelAttribute("album") Album album) {
        Album newALbum = albumRepository.findById(id);
        if(album.getName() != null) newALbum.setName(album.getName());
        if(album.getYear() != null) newALbum.setYear(album.getYear());

        return albumRepository.update(newALbum);
    }
}
