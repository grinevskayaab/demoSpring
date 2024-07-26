package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.dto.AuthorWithCash;
import com.github.grinevskayaab.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {

    @Query(value= """
            select a.*
            from authors a
                     join authors_songs aus on a.id = aus.author_id
                     join songs s on aus.song_id = s.id
                     join songs_stat stat on s.id = stat.song_id
            group by a.id
            order by sum(stat.count_plays) desc
            limit 1""",
            nativeQuery = true)
    Optional<Author> getAuthorTopByPlays();

    @Query(value= """
            select a.id,a.name,
                   round(
                           sum(stat.count_plays) * 100 /
                           cast(
                                   (select sum(stat.count_plays)
                                    from authors a
                                             join authors_songs aus on a.id = aus.author_id
                                             join songs s on aus.song_id = s.id
                                             join songs_stat stat on s.id = stat.song_id
                                    where date > date_trunc('month', NOW()) - '3 month'::INTERVAL)
                               as numeric)
                       , 2) * 1000000 / 100 as cash
            from authors a
                     join authors_songs aus on a.id = aus.author_id
                     join songs s on aus.song_id = s.id
                     join songs_stat stat on s.id = stat.song_id
            where date > date_trunc('month', NOW()) - '3 month'::INTERVAL
            GROUP BY a.id""", nativeQuery = true)
    List<AuthorWithCash> getAuthorsCash();
}
