package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
