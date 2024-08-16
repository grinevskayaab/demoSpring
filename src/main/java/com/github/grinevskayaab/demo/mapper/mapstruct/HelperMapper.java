package com.github.grinevskayaab.demo.mapper.mapstruct;

import com.github.grinevskayaab.demo.dto.AlbumSimpleDto;
import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.dto.SongSimpleDto;
import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.AuthorAlbum;
import com.github.grinevskayaab.demo.entity.AuthorSong;
import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.mapper.AuthorMapper;
import com.github.grinevskayaab.demo.mapper.SongMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HelperMapper {
    HelperMapper INSTANCE = Mappers.getMapper(HelperMapper.class);

    @Named("getAuthorSimpleDtoFromAuthorAlbum")
    default AuthorSimpleDto getAuthorSimpleDtoFromAuthorAlbum(AuthorAlbum authorAlbum) {
        return Mappers.getMapper(MapstructAuthorMapper.class).getSimpleDto(authorAlbum.getAuthor());
    }

    @Named("getAlbumSimpleDtoFromAuthorAlbum")
    default AlbumSimpleDto getAlbumSimpleDtoFromAuthorAlbum(AuthorAlbum authorAlbum) {
        return Mappers.getMapper(MapstructAlbumMapper.class).getSimpleDto(authorAlbum.getAlbum());
    }

    @Named("getAuthorSimpleDtoFromAuthorSong")
    default AuthorSimpleDto getAuthorSimpleDtoFromAuthorSong(AuthorSong authorSong) {
        return Mappers.getMapper(MapstructAuthorMapper.class).getSimpleDto(authorSong.getAuthor());
    }

    @Named("getSongSimpleDtoFromSong")
    default SongSimpleDto getSongSimpleDtoFromSong(Song song) {
        return Mappers.getMapper(MapstructSongMapper.class).getSimpleDto(song);
    }

    @Named("getAlbumSimpleDtoFromAlbum")
    default AlbumSimpleDto getAlbumSimpleDtoFromAlbum(Album album) {
        return Mappers.getMapper(MapstructAlbumMapper.class).getSimpleDto(album);
    }

    @Named("getSongSimpleDtoFromAuthorSong")
    default SongSimpleDto getSongSimpleDtoFromAuthorSong(AuthorSong authorSong) {
        return Mappers.getMapper(MapstructSongMapper.class).getSimpleDto(authorSong.getSong());
    }
}
