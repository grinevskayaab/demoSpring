package com.github.grinevskayaab.demo.repository;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.repository.mapper.AlbumMapper;
import com.github.grinevskayaab.demo.repository.mapper.SongMapper;
import com.github.grinevskayaab.demo.repository.mapper.SongWithoutAlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumRepository implements CrudRepository<Long, Album>{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AlbumRepository( JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Album findById(Long id) {
        try {
            Album album = jdbcTemplate.queryForObject("select * from albums where id=?", new AlbumMapper(), id);
            System.out.println(album);
            if(album != null) {
                List<Song> songs = findSongsByAlbumId(album);
                album.setSongs(songs);
            }
            System.out.println(album);

            return album;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Album> findAll() {
        List<Album> albums =  jdbcTemplate.query("select * from albums", new AlbumMapper());
        albums.forEach(album -> {
            List<Song> songs = findSongsByAlbumId(album);
            album.setSongs(songs);
            System.out.println(album);
        });

        return albums;
    }

    private List<Song> findSongsByAlbumId(Album album)  {
        List<Song> songs = jdbcTemplate.query("select * from songs where album_id=?", new SongWithoutAlbumMapper(), album.getId());
        songs.forEach(song -> song.setAlbum(album));
        return songs;
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
