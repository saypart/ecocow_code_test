package codeTest.ecocow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MOVIE_NOT_FOUND(404, "영화를 찾을 수 없습니다."),
    PERSON_NOT_FOUND(404, "영화를 찾을 수 없습니다."),
    PARTICIPATION_NOT_FOUND(404, "해당 건 찾을수 없습니다."),
    PARTICIPATION_ALREADY_EXISTS(405, "해당 건은 이미 등록되어 있습니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
