package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.Song;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByName(String name);

    @Override
    @EntityGraph(attributePaths={"authorSongs.song", "authorAlbums.album"})
    Optional<Author> findById(Long id);
}
