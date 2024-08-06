package com.github.grinevskayaab.demo.dto;


import java.util.List;

public record AuthorSimpleDto(Integer id, String name, List<Long> songIds, List<Long> albumIds){
}
