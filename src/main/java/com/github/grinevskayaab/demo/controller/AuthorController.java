package com.github.grinevskayaab.demo.controller;

import com.github.grinevskayaab.demo.dto.AuthorWithCash;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/top")
    public Author getAuthorTopByPlays() {
        return authorService.getAuthorTopByPlays();
    }

    @GetMapping("/cash")
    public List<AuthorWithCash> getAuthorsCash() {
        return authorService.getAuthorsCash();
    }


    @GetMapping()
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }
}
