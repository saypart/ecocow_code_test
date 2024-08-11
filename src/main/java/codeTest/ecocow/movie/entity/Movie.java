package codeTest.ecocow.movie.entity;

import codeTest.ecocow.participationList.ParticipationList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

//    @Column(nullable = false)
    private String title;

    private String original_title; //원제
    private String original_language; //원어
    private LocalDateTime screening_date; //상영일
    private int member_score;  //회원 점수
    private String one_line_summary;// 한줄요약
    private String outline; //개요
    private Long production_cost;  //제작비
    private Long profits;  // 수익
    private int running_time;  //상영시간

    private MovieStatus movieStatus; // 영화 개봉 상태

    @ElementCollection(targetClass = MovieGenre.class)
    @Enumerated(EnumType.STRING)  // Enum을 문자열로 저장
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<MovieGenre> genres;

    private Long keyword;

    public Long getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
