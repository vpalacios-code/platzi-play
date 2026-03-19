package com.platzi.platzi_play.persistence.entity;

import com.platzi.platzi_play.domain.dto.MovieDto;
import com.platzi.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi.platzi_play.domain.exception.MovieAlreadyExistsException;
import com.platzi.platzi_play.domain.exception.MovieNotFoundException;
import com.platzi.platzi_play.domain.repository.MovieRepository;
import com.platzi.platzi_play.persistence.crud.CrudMovieEntity;
import com.platzi.platzi_play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieEntityRepository implements MovieRepository {

    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }


    @Override
    public List<MovieDto> getALl() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(long id) {
        return this.movieMapper.toDto(this.crudMovieEntity.findById(id).orElse(null));
    }

    @Override
    public MovieDto save(MovieDto movieDto) {

        if(this.crudMovieEntity.findFirstByTitle(movieDto.title()) != null ) {
            throw new MovieAlreadyExistsException(movieDto.title());
        }

        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setStatus("D");
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {

        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if(movieEntity == null)
            throw new MovieNotFoundException("Película "+updateMovieDto.title()+" no existe.");

        this.movieMapper.updateEntityFromDto(updateMovieDto,movieEntity);

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public void delete(long id) {

        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        if(movieEntity == null)
            throw new MovieNotFoundException("Película no entrada.");

        this.crudMovieEntity.deleteById(id);
    }


}
