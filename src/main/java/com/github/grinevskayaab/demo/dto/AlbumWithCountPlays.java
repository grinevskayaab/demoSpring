package com.github.grinevskayaab.demo.dto;

import lombok.*;

//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
public interface AlbumWithCountPlays {
    Long getId();

    String getName();

    Long getCountPlaysAvg();
}
