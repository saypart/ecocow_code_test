package codeTest.ecocow.movie.dto;

import codeTest.ecocow.movie.entity.Movie;
import codeTest.ecocow.movie.entity.MovieStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MoviePatchDto {

    private Long movieId;
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

    private MovieStatus movieStatus;
    private Long keyword;
    // 영화 -> 장르 -> 1:N  @OneToMany  사용필요
    private Long movie_genre; //장르
    // 영화 -> 감독 및 배우 -> 1:N  @OneToMany  사용필요
    private Long related_person;

//    public void setMovie_Id(long movieId){this.movieId = movieId;}

}
