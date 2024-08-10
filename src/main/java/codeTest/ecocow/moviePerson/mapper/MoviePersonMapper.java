package codeTest.ecocow.moviePerson.mapper;

import codeTest.ecocow.movie.dto.MovieDto;
import codeTest.ecocow.movie.entity.Movie;
import codeTest.ecocow.moviePerson.dto.MoviePersonPatchDto;
import codeTest.ecocow.moviePerson.dto.MoviePersonPostDto;
import codeTest.ecocow.moviePerson.entity.MoviePerson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MoviePersonMapper {
    @Mapping(target = "personId", ignore = true)  //warning: Unmapped target property 오류 경고 무시 처리 매핑되지 않은 속성
    MoviePerson moviePersonPostDtoToMoviePerson(MoviePersonPostDto moviePersonPostDto);
    @Mapping(target = "personId")
    MoviePerson moviePersonPatchDtoToMoviePerson(MoviePersonPatchDto moviePersonPatchDto);
    MoviePerson moviePersonToMoviePersonResponseDto(MoviePerson moviePerson);

    MovieDto movieToMovieDto(Movie movie);
}
