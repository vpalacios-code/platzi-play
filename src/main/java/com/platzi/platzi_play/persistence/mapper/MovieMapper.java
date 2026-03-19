package com.platzi.platzi_play.persistence.mapper;

import com.platzi.platzi_play.domain.dto.MovieDto;
import com.platzi.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi.platzi_play.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenderMapper.class, StatusMapper.class})
public interface MovieMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGender" )
    @Mapping(source = "dateRelease", target = "dateRelease")
    @Mapping(source = "classification", target = "classification")
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToBoolean")
    MovieDto toDto(MovieEntity entity);
    List<MovieDto> toDto(Iterable<MovieEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "gender",target = "gender",qualifiedByName = "genderToString")
    @Mapping(source = "status", target = "status")
    MovieEntity toEntity(MovieDto dto);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "dateRelease", target = "dateRelease")
    @Mapping(source = "classification", target = "classification")
    void updateEntityFromDto(UpdateMovieDto updateMovieDto,@MappingTarget MovieEntity movieEntity);

}
