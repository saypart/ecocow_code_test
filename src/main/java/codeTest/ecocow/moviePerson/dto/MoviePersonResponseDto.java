package codeTest.ecocow.moviePerson.dto;

import codeTest.ecocow.moviePerson.entity.Gender;
import codeTest.ecocow.moviePerson.entity.Job;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class MoviePersonResponseDto {
    private Long personId;

    private String activity_name; //활동명

    private Gender gender; //성별
    private List<Job> jobs; //직업
    private LocalDateTime birthday; //출생일
    private String place_of_birth;// 출생지
    private int document_score;  //문서점수
    private List<String> another_name;  // 다른 이름들
}
