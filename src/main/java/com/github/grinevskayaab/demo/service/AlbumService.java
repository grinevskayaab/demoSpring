package com.github.grinevskayaab.demo.service;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.repository.AlbumRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbum(Long id) {

        return albumRepository.findById(id).orElse(null);
//        Album album = albumRepository.findById(id).orElse(null);
//        for(var song : album.getSongs()) {
//            System.out.println(song.getName());
//        }
//        return album;
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    public Album updateAlbum(Long id, Album album) {
        Optional<Album> newAlbum = albumRepository.findById(id);
        if (newAlbum.isPresent()) {
            Album updateAlbum = newAlbum.get();

            if (album.getName() != null) updateAlbum.setName(album.getName());
            if (album.getYear() != null) updateAlbum.setYear(album.getYear());
            return albumRepository.save(updateAlbum);
        }
        return null;
    }
}
