package com.github.grinevskayaab.demo.repository.mapper;

import com.github.grinevskayaab.demo.entity.Album;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AlbumMapper implements RowMapper<Album> {

    @Override
    public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Album(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getObject("year") == null ? null : rs.getInt("year")
        );
    }
}
