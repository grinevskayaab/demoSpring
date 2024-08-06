package com.github.grinevskayaab.demo.dto;


import java.util.List;

public record SongSimpleDto(Long id,
                            Integer year,
                            String name,
                            Long albumId,
                            List<Integer> authorIds) {
}
