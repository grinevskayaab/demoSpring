package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {
}
