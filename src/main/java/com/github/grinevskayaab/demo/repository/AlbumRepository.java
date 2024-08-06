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

//    @Override
//    @EntityGraph(attributePaths={"songs", "authors"})
//    Optional<Album> findById(Long id);


    @Query(value = "select distinct album from Album album left join fetch album.songs where album.name=:name")
    Optional<Album> findAByNameWithSongs(String name);

    @Query(value = "select distinct album from Album album left join fetch album.authors  where album.name=:name")
    Optional<Album> findByNameWithAuthors(String name);


    @Query(value = "select distinct album from Album album left join fetch album.songs where album.id=:id")
    Optional<Album> findAByIdWithSongs(Long id);

    @Query(value = "select distinct album from Album album left join fetch album.authors  where album.id=:id")
    Optional<Album> findByIdWithAuthors(Long id);


    @Query(value = "select distinct album from Album album left join fetch album.songs")
    List<Album> findAllWithSongs();

    @Query(value = "select distinct album from Album album left join fetch album.authors")
    List<Album> findAllWithAuthors();
}
