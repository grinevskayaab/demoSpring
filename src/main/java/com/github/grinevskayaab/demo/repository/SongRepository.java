package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.mappers.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

@Repository
@Component
public class SongRepository implements CrudRepository<Long, Song> {

    private final SongMapper songMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SongRepository(JdbcTemplate jdbcTemplate, SongMapper songMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.songMapper = songMapper;
    }

    @Override
    public Song findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from songs where id=?", songMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Song> findAll() {
        return jdbcTemplate.query("select * from songs", songMapper);
    }

    @Override
    public Song create(Song song) {

//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("name", song.getName(), Types.VARCHAR);
//        parameters.addValue("year", song.getYear(), Types.INTEGER);
//        parameters.addValue("album_id", null, Types.INTEGER );
//        SqlParameterSource data = new BeanPropertySqlParameterSource(song);
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update("insert into songs (name,year,album_id) values (:song)", data, keyHolder,
//                new String[]{"id"});

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
                song.getYear(),  song.getId());
         return song;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from authors_songs where song_id=?;" +
                "delete from songs_stat where song_id =?;" +
                "delete from songs where id=?", id, id, id);
    }

    public List<Song> getTopSingles() {
        return jdbcTemplate.query(" select s.name, sum(stat.count_plays) as sum, s.id, s.album_id, s.year\n" +
                "                from songs_stat stat\n" +
                "                         join songs s on stat.song_id = s.id\n" +
                "                where s.album_id is null\n" +
                "                  and stat.date > date_trunc('month', NOW()) - '6 month'::INTERVAL\n" +
                "                group by s.id\n" +
                "                order by sum desc\n" +
                "                limit 3;", new SongMapper());
    }
}
