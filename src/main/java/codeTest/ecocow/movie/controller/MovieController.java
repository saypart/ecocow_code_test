package codeTest.ecocow.movie.controller;

import codeTest.ecocow.movie.dto.MoviePatchDto;
import codeTest.ecocow.movie.dto.MoviePostDto;
import codeTest.ecocow.movie.entity.Movie;
import codeTest.ecocow.movie.mapper.MovieMapper;
import codeTest.ecocow.movie.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {
    private MovieService movieService;
    private MovieMapper movieMapper;

    //post
    @PostMapping("/add")
    public ResponseEntity postMovie(@Valid @RequestBody MoviePostDto moviePostDto) {
        Movie movie = movieService.createMovie(movieMapper.moviePostDtoToMovie(moviePostDto));

        return new ResponseEntity<>(
                movieMapper.movieToMovieResponseDto(movie),
                HttpStatus.CREATED);
    }

    //get
    @GetMapping("/{movieId}")
    public ResponseEntity getMovie(@PathVariable("movieId") long movieId) {
        Movie movie = movieService.findMovie(movieId);

        return new ResponseEntity<>(movieMapper.movieToMovieResponseDto(movie), HttpStatus.OK);
    }


    //patch
    @PatchMapping("/{movieId}")
    public ResponseEntity patchMovie(@PathVariable("movieId") long movieId,
                                         @Valid @RequestBody MoviePatchDto moviePatchDto) {
        moviePatchDto = MoviePatchDto.builder()
                .movieId(movieId)
                .title(moviePatchDto.getTitle())
                .original_title(moviePatchDto.getOriginal_title())
                .original_language(moviePatchDto.getOriginal_language())
                .screening_date(moviePatchDto.getScreening_date())
                .member_score(moviePatchDto.getMember_score())
                .one_line_summary(moviePatchDto.getOne_line_summary())
                .outline(moviePatchDto.getOutline())
                .production_cost(moviePatchDto.getProduction_cost())
                .profits(moviePatchDto.getProfits())
                .running_time(moviePatchDto.getRunning_time())
                .movieStatus(moviePatchDto.getMovieStatus())
                .keyword(moviePatchDto.getKeyword())
                .genres(moviePatchDto.getGenres())
                .related_person(moviePatchDto.getRelated_person())
                .build();

        Movie movie = movieService.updateMovie(movieMapper.moviePatchDtoToMovie(moviePatchDto));

        return new ResponseEntity<>(
                movieMapper.movieToMovieResponseDto(movie),HttpStatus.OK);
    }


    @DeleteMapping("/{movieId}")
    public ResponseEntity deleteMovie(@PathVariable("movieId") long movieId) {
        movieService.deleteMovie(movieId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
