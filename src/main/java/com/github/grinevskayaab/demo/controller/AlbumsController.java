package com.github.grinevskayaab.demo.controller;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@AllArgsConstructor
public class AlbumsController {

    private final AlbumService albumService;

    @GetMapping()
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable("id") Long id) {
         return albumService.getAlbum(id);
    }

    @PostMapping()
    public Album createAlbum(@ModelAttribute("album") Album album) {
        return albumService.createAlbum(album);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable("id") Long id) {
        albumService.deleteAlbum(id);
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable("id") Long id, @ModelAttribute("album") Album album) {
        return albumService.updateAlbum(id, album);
    }
}
