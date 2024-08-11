package codeTest.ecocow.movie.mapper;

import codeTest.ecocow.movie.dto.MovieDto;
import codeTest.ecocow.movie.dto.MoviePatchDto;
import codeTest.ecocow.movie.dto.MoviePostDto;
import codeTest.ecocow.movie.dto.MovieResponseDto;
import codeTest.ecocow.movie.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "movieId", ignore = true)  //warning: Unmapped target property 오류 경고 무시 처리 매핑되지 않은 속성
    Movie moviePostDtoToMovie(MoviePostDto moviePostDto);
    @Mapping(target = "movieId")
    Movie moviePatchDtoToMovie(MoviePatchDto moviePatchDto);
    MovieResponseDto movieToMovieResponseDto(Movie movie);

}
