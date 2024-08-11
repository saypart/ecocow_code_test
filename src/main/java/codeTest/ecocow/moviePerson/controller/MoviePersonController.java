package codeTest.ecocow.moviePerson.controller;

import codeTest.ecocow.moviePerson.dto.MoviePersonPatchDto;
import codeTest.ecocow.moviePerson.dto.MoviePersonPostDto;
import codeTest.ecocow.moviePerson.entity.MoviePerson;
import codeTest.ecocow.moviePerson.mapper.MoviePersonMapper;
import codeTest.ecocow.moviePerson.service.MoviePersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/moviePerson")
@AllArgsConstructor
public class MoviePersonController {

    final MoviePersonService moviePersonService;
    final MoviePersonMapper moviePersonMapper;

    @PostMapping("/add")
    public ResponseEntity postMoviePerson(@Valid @RequestBody MoviePersonPostDto moviePersonPostDto) {
        MoviePerson moviePerson = moviePersonService.createMoviePerson(moviePersonMapper.moviePersonPostDtoToMoviePerson(moviePersonPostDto));
        return new ResponseEntity<>(
                moviePersonMapper.moviePersonToMoviePersonResponseDto(moviePerson),
                HttpStatus.CREATED);
    }

    //get
    @GetMapping("/{personId}")
    public ResponseEntity getMoviePerson(@PathVariable("personId") long personId) {
        MoviePerson moviePerson = moviePersonService.findMoviePerson(personId);

        return new ResponseEntity<>(moviePersonMapper.moviePersonToMoviePersonResponseDto(moviePerson), HttpStatus.OK);
    }


    //patch
    @PatchMapping("/{personId}")
    public ResponseEntity patchMoviePerson(@PathVariable("personId") long personId,
                                     @Valid @RequestBody MoviePersonPatchDto moviePersonPatchDto) {
        moviePersonPatchDto = MoviePersonPatchDto.builder()
                .personId(personId)
                .activity_name(moviePersonPatchDto.getActivity_name())
                .gender(moviePersonPatchDto.getGender())
                .jobs(moviePersonPatchDto.getJobs())
                .birthday(moviePersonPatchDto.getBirthday())
                .place_of_birth(moviePersonPatchDto.getPlace_of_birth())
                .document_score(moviePersonPatchDto.getDocument_score())
                .another_name(moviePersonPatchDto.getAnother_name())
                .build();

        MoviePerson moviePerson = moviePersonService.updateMoviePerson(moviePersonMapper.moviePersonPatchDtoToMoviePerson(moviePersonPatchDto));

        return new ResponseEntity<>(
                moviePersonMapper.moviePersonToMoviePersonResponseDto(moviePerson),HttpStatus.OK);
    }


    @DeleteMapping("/{personId}")
    public ResponseEntity deleteMoviePerson(@PathVariable("personId") long personId) {
        moviePersonService.deleteMoviePerson(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
