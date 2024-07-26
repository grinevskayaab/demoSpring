package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.dto.AlbumWithCountPlays;
import com.github.grinevskayaab.demo.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query(value = """
            select al.*
            from albums al
                     join songs s on al.id = s.album_id
                     join songs_stat stat on s.id = stat.song_id
            where  stat.date > date_trunc('month', NOW()) - '6 month'::INTERVAL
            group by al.id
            order by sum(stat.count_plays) desc
            limit 3;""", nativeQuery = true)
    List<Album> getTopAlbums();


    @Query(value = """
            select id, name,
                   round(sum(sum_one_quarter) / count(id), 2) as countPlaysAvg
            from (select al.id as id, al.name, sum(stat.count_plays) as sum_one_quarter
                  from albums al
                           join songs s on al.id = s.album_id
                           join songs_stat stat on s.id = stat.song_id
                  group by al.id, date_trunc('quarter', stat.date)) as aisoq
            group by id, name""", nativeQuery = true)
    List<AlbumWithCountPlays> getAlbumWithCountPlays();
}
