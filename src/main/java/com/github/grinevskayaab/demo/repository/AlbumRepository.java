package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.Song;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Override
    @EntityGraph(attributePaths={"authorAlbums.author", "songs"})
    Optional<Album> findById(Long id);

    Optional<Album> findAlbumByName(String name);
}
