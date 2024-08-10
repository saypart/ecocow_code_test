package codeTest.ecocow.moviePerson.repository;

import codeTest.ecocow.moviePerson.entity.MoviePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MoviePersonRepository extends JpaRepository<MoviePerson, Long> {
    @Query(value = "SELECT c FROM MoviePerson c WHERE c.personId = :personId")
    Optional<MoviePerson> findByPersonId(long personId);
    List<MoviePerson> findByParticipationLists_Movie_MovieId(Long movieId);
}
