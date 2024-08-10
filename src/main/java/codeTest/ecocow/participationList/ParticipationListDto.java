package codeTest.ecocow.participationList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationListDto {
    private Long movieId;
    private String movieTitle;
    private Long personId;
    private String personName;

    public static class Builder {
        private Long movieId;
        private String movieTitle;
        private Long personId;
        private String personName;

        public Builder movieId(Long movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder movieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
            return this;
        }

        public Builder personId(Long personId) {
            this.personId = personId;
            return this;
        }

        public Builder personName(String personName) {
            this.personName = personName;
            return this;
        }

        public ParticipationListDto build() {
            ParticipationListDto participationListDto = new ParticipationListDto();
            participationListDto.movieId = this.movieId;
            participationListDto.movieTitle = this.movieTitle;
            participationListDto.personId = this.personId;
            participationListDto.personName = this.personName;
            return participationListDto;
        }
    }
}
