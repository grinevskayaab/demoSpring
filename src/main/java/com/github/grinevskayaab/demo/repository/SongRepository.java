package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.repository.mapper.SongMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

@Repository
@AllArgsConstructor
public class SongRepository implements CrudRepository<Long, Song> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Song findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from songs where id=?", new SongMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Song> findAll() {
        return jdbcTemplate.query("select * from songs", new SongMapper());
    }

    @Override
    public Song create(Song song) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into songs (name,year,album_id) values (?,?,?)", new String[]{"id"});
            ps.setString(1, song.getName());

            if (song.getYear() == null) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, song.getYear());
            }

            if (song.getAlbum() == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setLong(3, song.getAlbum().getId());
            }
            return ps;
        }, keyHolder);

        song.setId(Long.parseLong(keyHolder.getKey().toString()));
        return song;
    }

    @Override
    public Song update(Song song) {
        jdbcTemplate.update("update songs set name = ?, year = ? where id = ?", song.getName(),
                song.getYear(), song.getId());
        return song;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from authors_songs where song_id=?;" +
                "delete from songs_stat where song_id =?;" +
                "delete from songs where id=?", id, id, id);
    }

    public List<Song> getTopSingles() {
        return jdbcTemplate.query("""
                 select s.name, sum(stat.count_plays) as sum, s.id, s.album_id, s.year
                                from songs_stat stat
                                         join songs s on stat.song_id = s.id
                                where s.album_id is null
                                  and stat.date > date_trunc('month', NOW()) - '6 month'::INTERVAL
                                group by s.id
                                order by sum desc
                                limit 3;\
                """, new SongMapper());
    }
}
