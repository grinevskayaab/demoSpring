package com.github.grinevskayaab.demo.repository.mapper;

import com.github.grinevskayaab.demo.entity.Song;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongWithoutAlbumMapper implements RowMapper<Song> {
    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Song(
                rs.getLong("id"),
                rs.getObject("year") == null ? null : rs.getInt("year"),
                rs.getString("name"),
                null);
    }
}
