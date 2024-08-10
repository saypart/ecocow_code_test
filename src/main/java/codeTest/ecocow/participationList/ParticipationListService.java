package codeTest.ecocow.participationList;

import codeTest.ecocow.exception.BusinessLogicException;
import codeTest.ecocow.exception.ExceptionCode;
import codeTest.ecocow.movie.entity.Movie;
import codeTest.ecocow.movie.repository.MovieRepository;
import codeTest.ecocow.moviePerson.entity.MoviePerson;
import codeTest.ecocow.moviePerson.repository.MoviePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipationListService {

    @Autowired
    private ParticipationListRepository participationListRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MoviePersonRepository moviePersonRepository;

    @Autowired
    private ParticipationListMapper participationListMapper;


    //영화인물참여관계 생성
    public ParticipationListDto createParticipationList(ParticipationListDto participationListDto) {
        Movie movie = movieRepository.findById(participationListDto.getMovieId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MOVIE_NOT_FOUND));
        MoviePerson person = moviePersonRepository.findById(participationListDto.getPersonId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.PERSON_NOT_FOUND));

        // 중복 확인
        Optional<ParticipationList> existingParticipation = participationListRepository.findByMovie_MovieIdAndMoviePerson_PersonId(
                participationListDto.getMovieId(), participationListDto.getPersonId());
        // 중복 등록 방지
        if (existingParticipation.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.PARTICIPATION_ALREADY_EXISTS);
        }

        // ParticipationList 생성 및 저장
        ParticipationList participationList = new ParticipationList.Builder()
                .movie(movie)
                .person(person)
                .build();

        participationList = participationListRepository.save(participationList);

        return participationListMapper.participationListToParticipationListDto(participationList);
    }

    //영화인물참여관계 불러오기
    public ParticipationList findByParticipationList_ParticipationListId(Long participationListId) {
        return findVerifiedParticipationList(participationListId);
    }

    public ParticipationList findVerifiedParticipationList(long participationListId){
        Optional<ParticipationList> optionalParticipationList = participationListRepository.findByParticipationListId(participationListId);
        ParticipationList findParticipationList =
                optionalParticipationList.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.PARTICIPATION_NOT_FOUND));
        return findParticipationList;
    }
    //삭제
    public void deleteParticipationList(long participationListId) {
        ParticipationList participationList = findVerifiedParticipationList(participationListId);
        participationListRepository.delete(participationList);
    }
}
