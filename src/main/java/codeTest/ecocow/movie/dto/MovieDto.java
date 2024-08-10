package codeTest.ecocow.movie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieDto {
    private Long movieId;
    private String title;
}