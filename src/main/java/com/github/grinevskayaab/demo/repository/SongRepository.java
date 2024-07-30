package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
//    @Query(
//            value = """
//                    select s.*
//                    from songs_stat stat
//                             join songs s on stat.song_id = s.id
//                    where s.album_id is null
//                      and stat.date > date_trunc('month', NOW()) - '6 month'::INTERVAL
//                    group by s.id
//                    order by sum(stat.count_plays) desc
//                    limit 3;""",
//            nativeQuery = true)

//    List<Song> findSongsByAlbumIsNull();

    @Query(value= """
            select song from Song song Join song.stat stat
            where song.album.id is null
            and stat.date >= :date
            group by song.id
            order by sum(stat.countPlays) desc
            limit 3
          """)
    List<Song> getTosSingles(Timestamp date);

//    @Query(value= """
//            select s.*
//            from authors a
//                     join authors_songs aus on a.id = aus.author_id
//                     join songs s on aus.song_id = s.id
//                     join songs_stat stat on s.id = stat.song_id
//            where date > date_trunc('month', NOW()) - '3 month'::INTERVAL
//            GROUP BY s.id
//            having count(aus.song_id) > 2
//            order by sum(stat.count_plays) desc
//            limit 3""",
//            nativeQuery = true)
//    List<Song> getTopFeats();

@Query(value= """
            select song
            from Author a join a.songs song join song.stat stat
            where stat.date >= :date
            group by song
            having count(a) >= 2
            order by sum(stat.countPlays) desc
             limit 3
           """)
List<Song> getTopFeats(Timestamp date);


}
