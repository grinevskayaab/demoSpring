package com.github.grinevskayaab.demo.mapper.modelmapper;

import com.github.grinevskayaab.demo.dto.AlbumFullDto;
import com.github.grinevskayaab.demo.dto.AlbumSimpleDto;
import com.github.grinevskayaab.demo.entity.Album;
import com.github.grinevskayaab.demo.entity.AuthorAlbum;
import com.github.grinevskayaab.demo.mapper.AlbumMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class ModelAlbumMapper implements AlbumMapper {
    private final ModelMapper modelMapper;

    public ModelAlbumMapper() {
        this.modelMapper = new ModelMapper();
        Converter<List<AuthorAlbum>, List<AlbumSimpleDto>> authorToAuthorSimpleDto =
                new AbstractConverter<>() {
                    @Override
                    protected List<AlbumSimpleDto> convert(List<AuthorAlbum> authorAlbums) {
                        return authorAlbums.stream().map(el -> modelMapper.map(el.getAlbum(), AlbumSimpleDto.class)).toList();
                    }
                };

        modelMapper.createTypeMap(Album.class, AlbumFullDto.class)
                .addMappings(
                        mapper -> mapper.using(authorToAuthorSimpleDto).map(Album::getAuthorAlbums, AlbumFullDto::setAuthors)
                );
    }

    @Override
    public List<AlbumSimpleDto> getSimpleDto(List<Album> albums) {
        return albums.stream().map(album -> modelMapper.map(album, AlbumSimpleDto.class)).toList();
    }

    @Override
    public AlbumSimpleDto getSimpleDto(Album album) {
        return modelMapper.map(album, AlbumSimpleDto.class);
    }

    @Override
    public AlbumFullDto getFullDto(Album album) {

        return modelMapper.map(album, AlbumFullDto.class);
    }
}
