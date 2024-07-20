package com.github.grinevskayaab.demo.mappers;

import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Song;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SongMapper implements RowMapper<Song> {
    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Song(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getObject("year") == null ? null : rs.getInt("year"),
                rs.getObject("album_id") == null ? null : new Album(rs.getLong("album_id")));
    }
}
