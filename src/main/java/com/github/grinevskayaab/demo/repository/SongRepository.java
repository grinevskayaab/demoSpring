package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Song;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
    @Override
    @EntityGraph(attributePaths = "authors")
    Optional<Song> findById(Long id);

    @Override
    @EntityGraph(attributePaths = "authors")
    List<Song> findAll();

    @EntityGraph(attributePaths = "authors")
    Optional<Song> findByName(String name);
}
