package codeTest.ecocow.participationList;

import codeTest.ecocow.movie.dto.MoviePatchDto;
import codeTest.ecocow.movie.entity.Movie;
import codeTest.ecocow.moviePerson.entity.MoviePerson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipationListMapper {
    @Mapping(source = "movie.movieId", target = "movieId")
    @Mapping(source = "movie.title", target = "movieTitle")
    @Mapping(source = "moviePerson.personId", target = "personId")
    @Mapping(source = "moviePerson.activity_name", target = "personName")
    ParticipationListDto participationListToParticipationListDto(ParticipationList participationList);

    @Mapping(target = "movie", expression = "java(movieFromId(participationListDto.getMovieId()))")
    @Mapping(target = "moviePerson", expression = "java(personFromId(participationListDto.getPersonId()))")
    ParticipationList participationListDtoToParticipationList(ParticipationListDto participationListDto);

    @Mapping(target = "participationListId")
    ParticipationList participationListToMovieParticipationListDto(ParticipationList participationList);

    // 수동 매핑을 위해 사용되는 헬퍼 메서드
    default Movie movieFromId(Long movieId) {
        if (movieId == null) {
            return null;
        }
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        return movie;
    }

    default MoviePerson personFromId(Long personId) {
        if (personId == null) {
            return null;
        }
        MoviePerson person = new MoviePerson();
        person.setPersonId(personId);
        return person;
    }
}
