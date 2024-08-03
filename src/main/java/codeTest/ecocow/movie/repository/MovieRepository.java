package codeTest.ecocow.movie.repository;

import codeTest.ecocow.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT c FROM Movie c WHERE c.movieId = :movieId")
    Optional<Movie> findByMovie(long movieId);
}
