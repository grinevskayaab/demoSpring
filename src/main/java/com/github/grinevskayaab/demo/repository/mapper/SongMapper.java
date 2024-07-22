package com.github.grinevskayaab.demo.repository.mapper;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Song;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SongMapper implements RowMapper<Song> {

    Map<Long, Album> albums = new HashMap<>();

    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long album_id = rs.getObject("album_id") == null ? null : rs.getLong("album_id");
        Album album = null;

        if (album_id != null) {
            albums.putIfAbsent(album_id, new Album(album_id));
            album = albums.get(album_id);
        }
        return new Song(
                rs.getLong("id"),
                rs.getObject("year") == null ? null : rs.getInt("year"),
                rs.getString("name"),
                album);
    }

}
