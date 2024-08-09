package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByName(String name);


    @Query(value = "select distinct author from Author author left join fetch author.authorAlbums aul left join fetch aul.album where author.id=:id")
    Optional<Author> findByIdWithAlbums(Long id);

    @Query(value = "select distinct author from Author author left join fetch author.authorSongs aus left join fetch aus.song where author.id=:id")
    Optional<Author> findByIdWithSongs(Long id);
}
