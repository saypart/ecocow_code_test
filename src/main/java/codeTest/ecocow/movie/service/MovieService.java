package codeTest.ecocow.movie.service;

import codeTest.ecocow.exception.BusinessLogicException;
import codeTest.ecocow.exception.ExceptionCode;
import codeTest.ecocow.movie.entity.Movie;
import codeTest.ecocow.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // 생성자 를 위한 추가
public class MovieService {

    private final MovieRepository movieRepository;


    //영화 생성하기
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    //영화 가져오기
    public Movie findMovie(Long movieId) {
        return findVerifiedMovie(movieId);
    }

    public Movie findVerifiedMovie(long movieId){
        Optional<Movie> optionalMember = movieRepository.findByMovie(movieId);
        Movie findMovie =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MOVIE_NOT_FOUND));
        return findMovie;
    }
    //영화 수정하기
    public Movie updateMovie(Movie movie) {
        if (movie.getMovieId() == null) {
            throw new IllegalArgumentException("Movie ID cannot be null");
        }

        Movie findMovie = findVerifiedMovie(movie.getMovieId());

        Movie updatedMovie = findMovie.toBuilder()
                .title(Optional.ofNullable(movie.getTitle()).orElse(findMovie.getTitle()))
                .original_title(Optional.ofNullable(movie.getOriginal_title()).orElse(findMovie.getOriginal_title()))
                .original_language(Optional.ofNullable(movie.getOriginal_language()).orElse(findMovie.getOriginal_language()))
                .screening_date(Optional.ofNullable(movie.getScreening_date()).orElse(findMovie.getScreening_date()))
                .member_score(Optional.ofNullable(movie.getMember_score()).orElse(findMovie.getMember_score()))
                .one_line_summary(Optional.ofNullable(movie.getOne_line_summary()).orElse(findMovie.getOne_line_summary()))
                .outline(Optional.ofNullable(movie.getOutline()).orElse(findMovie.getOutline()))
                .production_cost(Optional.ofNullable(movie.getProduction_cost()).orElse(findMovie.getProduction_cost()))
                .profits(Optional.ofNullable(movie.getProfits()).orElse(findMovie.getProfits()))
                .running_time(Optional.ofNullable(movie.getRunning_time()).orElse(findMovie.getRunning_time()))
                .movieStatus(Optional.ofNullable(movie.getMovieStatus()).orElse(findMovie.getMovieStatus()))
                .keyword(Optional.ofNullable(movie.getKeyword()).orElse(findMovie.getKeyword()))
                .genres(Optional.ofNullable(movie.getGenres()).orElse(findMovie.getGenres()))
                .related_person(Optional.ofNullable(movie.getRelated_person()).orElse(findMovie.getRelated_person()))
                .build();

        return movieRepository.save(updatedMovie);
    }
    //영화 삭제하기
    public void deleteMovie(long movieId) {
        Movie movie = findVerifiedMovie(movieId);
        movieRepository.delete(movie);
    }
}


