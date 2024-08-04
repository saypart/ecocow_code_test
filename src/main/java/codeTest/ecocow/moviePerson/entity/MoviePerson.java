package codeTest.ecocow.moviePerson.entity;

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
public class MoviePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    private String activity_name; //활동명
    @Enumerated(EnumType.STRING)
    private Gender gender; //성별

    @ElementCollection
    @CollectionTable(name = "person_jobs", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "job")
    private List<Job> jobs; //직업
    private LocalDateTime birthday; //출생일
    private String place_of_birth;// 출생지
    private String participating_works; //참여작품
    private int document_score;  //문서점수

    @ElementCollection
    @CollectionTable(name = "person_another_names", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "another_name")
    private List<String> another_name;  // 다른 이름들
}
