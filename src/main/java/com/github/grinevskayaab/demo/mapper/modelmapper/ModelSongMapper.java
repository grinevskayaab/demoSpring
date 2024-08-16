package com.github.grinevskayaab.demo.mapper.modelmapper;

import com.github.grinevskayaab.demo.dto.AlbumSimpleDto;
import com.github.grinevskayaab.demo.dto.AuthorSimpleDto;
import com.github.grinevskayaab.demo.dto.SongFullDto;
import com.github.grinevskayaab.demo.dto.SongSimpleDto;
import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.Author;
import com.github.grinevskayaab.demo.entity.AuthorSong;
import com.github.grinevskayaab.demo.entity.Song;
import com.github.grinevskayaab.demo.mapper.SongMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
public class ModelSongMapper implements SongMapper {

    private final ModelMapper modelMapper;
    private final HelperModelMapper helper;

    public ModelSongMapper() {
        this.modelMapper = new ModelMapper();
        this.helper = new HelperModelMapper();

    }

    @Override
    public SongSimpleDto getSimpleDto(Song song) {
        return modelMapper.map(song, SongSimpleDto.class);
    }


    @Override
    public List<SongSimpleDto> getSimpleDto(List<Song> songs) {
        return songs.stream().map(song -> modelMapper.map(song, SongSimpleDto.class)).toList();
    }

    @Override
    public SongFullDto getFullDto(Song song) {
//        Converter<List<AuthorSong>, List<AuthorSimpleDto>> authorToAuthorSimpleDto =
//                new AbstractConverter<>() {
//                    @Override
//                    protected List<AuthorSimpleDto> convert(List<AuthorSong> authorSongs) {
//                        return authorSongs.stream().map(el -> modelMapper.map(el.getAuthor(), AuthorSimpleDto.class)).toList();
//                    }
//                };


        modelMapper.typeMap(Song.class, SongFullDto.class)
                .addMappings(
                        mapper -> mapper.using(helper.getAuthorSongToAuthorSimpleDto()).map(Song::getAuthorSongs, SongFullDto::setAuthors)
                );
        return modelMapper.map(song, SongFullDto.class);
    }
}
