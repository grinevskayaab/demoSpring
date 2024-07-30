package com.github.grinevskayaab.demo.service;

import com.github.grinevskayaab.demo.dto.AuthorWithCash;
import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author getAuthorTopByPlays() {
        return authorRepository.getAuthorTopByPlays().orElse(null);
    }

    public List<AuthorWithCash> getAuthorsCash() {
        return authorRepository.getAuthorsCash(Timestamp.valueOf(LocalDateTime.now().minusMonths(3)));
    }
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
