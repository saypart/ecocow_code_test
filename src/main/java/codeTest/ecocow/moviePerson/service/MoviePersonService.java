package codeTest.ecocow.moviePerson.service;

import codeTest.ecocow.exception.BusinessLogicException;
import codeTest.ecocow.exception.ExceptionCode;
import codeTest.ecocow.moviePerson.entity.MoviePerson;
import codeTest.ecocow.moviePerson.repository.MoviePersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoviePersonService {

    private final MoviePersonRepository moviePersonRepository;


    //영화인물 생성하기
    public MoviePerson createMoviePerson(MoviePerson moviePerson) {
        return moviePersonRepository.save(moviePerson);
    }
    //영화인물 가져오기
    public MoviePerson findMoviePerson(Long personId) {
            return findVerifiedMoviePerson(personId);
        }

    public MoviePerson findVerifiedMoviePerson(long personId){
        Optional<MoviePerson> optionalMember = moviePersonRepository.findByPersonId(personId);
        MoviePerson findMoviePerson =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MOVIE_NOT_FOUND));
        return findMoviePerson;
    }

    //영화인물 수정하기
    public MoviePerson updateMoviePerson(MoviePerson moviePerson) {
        MoviePerson findMoviePerson = findVerifiedMoviePerson(moviePerson.getPersonId());

        MoviePerson updatedMoviePerson = findMoviePerson.toBuilder()
                .activity_name(Optional.ofNullable(moviePerson.getActivity_name()).orElse(findMoviePerson.getActivity_name()))
                .gender(Optional.ofNullable(moviePerson.getGender()).orElse(findMoviePerson.getGender()))
                .jobs(Optional.ofNullable(moviePerson.getJobs()).orElse(findMoviePerson.getJobs()))
                .birthday(Optional.ofNullable(moviePerson.getBirthday()).orElse(findMoviePerson.getBirthday()))
                .place_of_birth(Optional.ofNullable(moviePerson.getPlace_of_birth()).orElse(findMoviePerson.getPlace_of_birth()))
                .participating_works(Optional.ofNullable(moviePerson.getParticipating_works()).orElse(findMoviePerson.getParticipating_works()))
                .document_score(Optional.ofNullable(moviePerson.getDocument_score()).orElse(findMoviePerson.getDocument_score()))
                .another_name(Optional.ofNullable(moviePerson.getAnother_name()).orElse(findMoviePerson.getAnother_name()))
                .build();

        return moviePersonRepository.save(updatedMoviePerson);
        }
    //영화인물 삭제하기
    public void deleteMoviePerson(long personId) {
        MoviePerson moviePerson = findVerifiedMoviePerson(personId);
        moviePersonRepository.delete(moviePerson);
    }
}

