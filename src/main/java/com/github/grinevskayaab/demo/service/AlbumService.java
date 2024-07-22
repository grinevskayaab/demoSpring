package com.github.grinevskayaab.demo.service;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;


    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbum(Long id) {
        return albumRepository.findById(id);
    }

    public Album createAlbum(Album album) {
        return albumRepository.create(album);
    }

    public void deleteAlbum(Long id) {
        albumRepository.delete(id);
    }

    public Album updateAlbum(Long id, Album album) {
        Album newALbum = albumRepository.findById(id);
        if (album.getName() != null) newALbum.setName(album.getName());
        if (album.getYear() != null) newALbum.setYear(album.getYear());

        return albumRepository.update(newALbum);
    }
}
