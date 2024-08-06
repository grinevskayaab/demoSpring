package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {

    @Override
    @EntityGraph(attributePaths = {"songs","albums"})
    Optional<Author> findById(Long id);

    @Query(value="Select distinct author from Author author left join fetch author.songs where author.name=:name")
    Optional<Author> findByNameWithSongs(String name);

    @Query(value="Select distinct author from Author author left join fetch author.albums where author.name=:name")
    Optional<Author> findByNameWithAlbums(String name);


    @Query(value="Select distinct author from Author author left join fetch author.songs")
    List<Author> findAllWithSongs();

    @Query(value="Select distinct author from Author author left join fetch author.albums")
    List<Author> findAllWithAlbums();
}
