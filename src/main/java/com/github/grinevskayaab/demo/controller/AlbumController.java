package com.github.grinevskayaab.demo.controller;

import com.github.grinevskayaab.demo.dto.AlbumFullDto;
import com.github.grinevskayaab.demo.dto.AlbumSimpleDto;
import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.mapper.AlbumMapper;
import com.github.grinevskayaab.demo.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    @GetMapping()
    public List<AlbumSimpleDto> getAlbums() {
        return albumMapper.getSimpleDto(albumService.getAlbums());
    }

    @GetMapping("/{id}")
    public AlbumFullDto getAlbum(@PathVariable("id") Long id) {
         return albumMapper.getFullDto(albumService.getAlbum(id));
    }

    @GetMapping("/name/{name}")
    public AlbumSimpleDto getAlbumByName(@PathVariable("name") String name) {
        return albumMapper.getSimpleDto(albumService.getAlbumByName(name));
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
