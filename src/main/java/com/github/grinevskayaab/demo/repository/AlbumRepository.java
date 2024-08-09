package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query(value = "select distinct album from Album album left join fetch album.songs where album.id=:id")
    Optional<Album> findByIdWithSongs(Long id);

    @Query(value = "select distinct album from Album album left join fetch album.authorAlbums aul left join fetch aul.author where album.id=:id")
    Optional<Album> findByIdWithAuthors(Long id);

    Optional<Album> findAlbumByName(String name);
}
