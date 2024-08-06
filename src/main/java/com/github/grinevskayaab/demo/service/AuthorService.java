package com.github.grinevskayaab.demo.service;

import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        List<Author> authors = authorRepository.findAllWithAlbums();
        return !authors.isEmpty() ?
                authorRepository.findAllWithSongs() :
                authors;
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author getAuthorByName(String name) {
        Optional<Author> author = authorRepository.findByNameWithSongs(name);
        return author.isPresent() ? authorRepository.findByNameWithAlbums(name).orElse(null) : null;
    }
}
