package com.github.grinevskayaab.demo.entity;

import com.github.grinevskayaab.demo.repository.AuthorRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name="authors")
@EnableJdbcRepositories(basePackageClasses = AuthorRepository.class)
public class Author {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="authors_id_seq", sequenceName = "authors_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "authors_is_seq")
    private Integer id;

    @Column
    private String name;
}
