package codeTest.ecocow.restdocs.Movie;

import codeTest.ecocow.movie.controller.MovieController;
import codeTest.ecocow.movie.dto.MovieResponseDto;
import codeTest.ecocow.movie.entity.Movie;
import codeTest.ecocow.movie.entity.MovieGenre;
import codeTest.ecocow.movie.entity.MovieStatus;
import codeTest.ecocow.movie.mapper.MovieMapper;
import codeTest.ecocow.participationList.ParticipationList;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import codeTest.ecocow.movie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;


@SpringJUnitConfig
@AutoConfigureMockMvc
@WebMvcTest(MovieController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MovieGet {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService; // MovieService를 Mocking

    @MockBean
    private MovieMapper movieMapper; // MovieMapper를 Mocking

    @Test
    public void getMovieTest() throws Exception {
        System.out.println("mockMvc: " + mockMvc);
        System.out.println("movieService: " + movieService);
        // 테스트 데이터 생성
        long movieId = 1L;

        // 테스트 데이터 생성 (빌더 패턴 사용)

        Movie movie = Movie.builder()
                .movieId(movieId)
                .title("데드풀과 울버린")
                .original_title("Deadpool & Wolverine")
                .original_language("영어")
                .screening_date(LocalDateTime.of(2024, 7, 26, 12, 0, 0))
                .member_score(80)
                .one_line_summary("히어로 생활에서 은퇴한 후, 평범한 중고차 딜러로 살아가던 ‘데드풀’이 예상치 못한 거대한 위기를 맞아 모든 면에서 상극인 ‘울버린’을 찾아가게 되며 펼쳐지는 도파민 폭발 액션 블록버스터")
                .outline("마블 시네마틱 유니버스 페이즈 5의 네 번째 영화. 데드풀 실사영화 시리즈의 세 번째 작품이며, 울버린 실사영화 시리즈와 크로스오버를 겸하고 있다. 엑스맨 유니버스의 마블 시네마틱 유니버스 편입 이후 첫 데드풀 영화이며, 기존의 20세기 폭스가 아닌 마블 스튜디오에서 제작한 엑스맨 유니버스 배경 작품이다. 마블 시네마틱 유니버스 최초의 R등급이자 청소년 관람불가 영화이기도 하다.")
                .production_cost(200000000L)
                .profits(null)
                .running_time(128)
                .movieStatus(MovieStatus.Before_release)
                .genres(Arrays.asList(MovieGenre.SF,MovieGenre.고어,MovieGenre.슈퍼히어로,MovieGenre.안티히어로,MovieGenre.액션,MovieGenre.블랙코미디,MovieGenre.어드벤처,MovieGenre.버디))
                .keyword(11L)
                .build();

        MovieResponseDto movieResponseDto = MovieResponseDto.builder()
                .movieId(movieId)
                .title("데드풀과 울버린")
                .original_title("Deadpool & Wolverine")
                .original_language("영어")
                .screening_date(LocalDateTime.of(2024, 7, 26, 12, 0, 0))
                .member_score(80)
                .outline("마블 시네마틱 유니버스 페이즈 5의 네 번째 영화. 데드풀 실사영화 시리즈의 세 번째 작품이며, 울버린 실사영화 시리즈와 크로스오버를 겸하고 있다. 엑스맨 유니버스의 마블 시네마틱 유니버스 편입 이후 첫 데드풀 영화이며, 기존의 20세기 폭스가 아닌 마블 스튜디오에서 제작한 엑스맨 유니버스 배경 작품이다. 마블 시네마틱 유니버스 최초의 R등급이자 청소년 관람불가 영화이기도 하다.")
                .one_line_summary("히어로 생활에서 은퇴한 후, 평범한 중고차 딜러로 살아가던 ‘데드풀’이 예상치 못한 거대한 위기를 맞아 모든 면에서 상극인 ‘울버린’을 찾아가게 되며 펼쳐지는 도파민 폭발 액션 블록버스터")
                .production_cost(200000000L)
                .movieStatus(MovieStatus.valueOf("Before_release"))
                .profits(10000L)
                .running_time(128)
                .genres(Arrays.asList(MovieGenre.SF,MovieGenre.고어,MovieGenre.슈퍼히어로,MovieGenre.안티히어로,MovieGenre.액션,MovieGenre.블랙코미디,MovieGenre.어드벤처,MovieGenre.버디))
                .keyword(11L)
                .build();


        // Mocking: movieService.getMovieById(movieId)를 호출하면 movie 객체를 반환
        given(movieService.findMovie(movieId)).willReturn(movie);
        given(movieMapper.movieToMovieResponseDto(movie)).willReturn(movieResponseDto);



        //when
        ResultActions actions =
            mockMvc.perform(
                    get("/movie/{movieId}", movieId)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                );


        //then 문서화
        actions
                .andExpect(status().isOk())
                .andDo(document("get-movie",preprocessResponse(prettyPrint()),
                        responseFields(
                                List.of(
                                        fieldWithPath("movieId").type(JsonFieldType.NUMBER).description("movie 식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("영화제목"),
                                        fieldWithPath("original_title").type(JsonFieldType.STRING).description("영화 원 제목"),
                                        fieldWithPath("original_language").type(JsonFieldType.STRING).description("원어"),
                                        fieldWithPath("screening_date").type(JsonFieldType.STRING).description("개봉일"),
                                        fieldWithPath("member_score").type(JsonFieldType.NUMBER).description("평가점수"),
                                        fieldWithPath("outline").type(JsonFieldType.STRING).description("개요"),
                                        fieldWithPath("one_line_summary").type(JsonFieldType.STRING).description("한줄 소개"),
                                        fieldWithPath("production_cost").type(JsonFieldType.NUMBER).description("제작비"),
                                        fieldWithPath("movieStatus").type(JsonFieldType.STRING).description("개봉상태"),
                                        fieldWithPath("profits").type(JsonFieldType.NUMBER).description("수익"),
                                        fieldWithPath("running_time").type(JsonFieldType.NUMBER).description("상영시간"),
                                        fieldWithPath("genres").type(JsonFieldType.ARRAY).description("장르"),
                                        fieldWithPath("keyword").type(JsonFieldType.NUMBER).description("키워드")
                                )
                        )
                ));







//        when(movieMapper.moviePostDtoToMovie(any(MoviePostDto.class))).thenReturn(movie);
//        when(movieService.createMovie(any(Movie.class))).thenReturn(movie);
//        when(movieMapper.movieToMovieResponseDto(any(Movie.class))).thenReturn(movieResponseDto);
//
//        when(movieController.getMovie(movieId)).thenReturn(movieMapper.movieToMovieResponseDto(movie));

        // GET 요청을 통해 컨트롤러를 호출하고 응답을 검증
//        mockMvc.perform(MockMvcRequestBuilders.get("/movie/{movieId}", movieId)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId").value(movieId))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("데드풀과 울버린"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.original_title").value("Deadpool & Wolverine"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.original_language").value("영어"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.screening_date").value("2024-07-26T12:00:00"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.member_score").value(80))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.one_line_summary").value("히어로 생활에서 은퇴한 후, 평범한 중고차 딜러로 살아가던 ‘데드풀’이 예상치 못한 거대한 위기를 맞아 모든 면에서 상극인 ‘울버린’을 찾아가게 되며 펼쳐지는 도파민 폭발 액션 블록버스터"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.outline").value("마블 시네마틱 유니버스 페이즈 5의 네 번째 영화. 데드풀 실사영화 시리즈의 세 번째 작품이며, 울버린 실사영화 시리즈와 크로스오버를 겸하고 있다. 엑스맨 유니버스의 마블 시네마틱 유니버스 편입 이후 첫 데드풀 영화이며, 기존의 20세기 폭스가 아닌 마블 스튜디오에서 제작한 엑스맨 유니버스 배경 작품이다. 마블 시네마틱 유니버스 최초의 R등급이자 청소년 관람불가 영화이기도 하다."))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.production_cost").value(200000000))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.movieStatus").value("Before_release"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.profits").value(nullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.running_time").value(128))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[0]").value("SF"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[1]").value("고어"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[2]").value("슈퍼히어로"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[3]").value("안티히어로"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[4]").value("액션"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[5]").value("블랙코미디"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[6]").value("어드벤처"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genres[7]").value("버디"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.keyword").value(nullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.related_person").value(nullValue()));
    }
}