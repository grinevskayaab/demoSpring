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
        return authorRepository.findAll();
    }

    public Author getAuthor(Long id) {
        Optional<Author> author = authorRepository.findByIdWithSongs(id);

        return author.isPresent()? authorRepository.findByIdWithAlbums(id).orElse(null): null;
    }

    public Author getAuthorByName(String name) {
        return authorRepository.findAuthorByName(name).orElse(null);
    }
}
