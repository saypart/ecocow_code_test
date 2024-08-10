package codeTest.ecocow.participationList;

import codeTest.ecocow.movie.entity.Movie;
import codeTest.ecocow.moviePerson.entity.MoviePerson;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParticipationList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participationListId;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private MoviePerson moviePerson;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    private Movie movie;

    @Getter
    public static class Builder {
        private Movie movie;
        private MoviePerson moviePerson;

        public Builder movie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder person(MoviePerson moviePerson) {
            this.moviePerson = moviePerson;
            return this;
        }

        public ParticipationList build() {
            ParticipationList participationList = new ParticipationList();
            participationList.movie = this.movie;
            participationList.moviePerson = this.moviePerson;
            return participationList;
        }
    }
}
