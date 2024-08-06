package com.github.grinevskayaab.demo.controller;

import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.mapper.AuthorMapper;
import com.github.grinevskayaab.demo.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping()
    public List<AuthorSimpleDto> getAuthors() {
        return authorMapper.getSimpleDto(authorService.getAuthors());
    }


    @GetMapping("/{id}")
    public AuthorSimpleDto getAuthor(@PathVariable("id") Long id) {
        return authorMapper.getSimpleDto(authorService.getAuthor(id));
    }

    @GetMapping("/name/{name}")
    public AuthorSimpleDto getAuthor(@PathVariable("name") String name) {
        return authorMapper.getSimpleDto(authorService.getAuthorByName(name));
    }
}
