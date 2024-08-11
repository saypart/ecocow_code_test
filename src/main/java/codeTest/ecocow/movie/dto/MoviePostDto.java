package codeTest.ecocow.movie.dto;

import codeTest.ecocow.movie.entity.MovieGenre;
import codeTest.ecocow.movie.entity.MovieStatus;
import codeTest.ecocow.participationList.ParticipationList;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MoviePostDto {
    @NotNull(message = "이름은 Null 일 수 없습니다!")
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
    private MovieStatus movieStatus = MovieStatus.Before_release; //상태  기본값 개봉전으로 설정하기
    private List<MovieGenre> genres;

    private Long keyword;
}
