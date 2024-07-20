package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.mappers.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

@Repository
public class AlbumRepository implements CrudRepository<Long, Album>{
    private final AlbumMapper albumMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AlbumRepository(AlbumMapper albumMapper, JdbcTemplate jdbcTemplate) {
        this.albumMapper = albumMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Album findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from albums where id=?", albumMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Album> findAll() {
        return jdbcTemplate.query("select * from songs", albumMapper);
    }

    @Override
    public Album create(Album album) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into albums (name,year) values (?,?)", new String[]{"id"});
            ps.setString(1, album.getName());

            if (album.getYear() == null) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, album.getYear());
            }
            return ps;
        }, keyHolder);

        album.setId(Long.parseLong(keyHolder.getKey().toString()));
        return album;
    }

    @Override
    public Album update(Album album) {
        jdbcTemplate.update("update albums set name = ?, year = ? where id = ?", album.getName(),
                album.getYear(),  album.getId());
        return album;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "delete from albums where id=?", id);
    }
}
