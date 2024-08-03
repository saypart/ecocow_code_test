package codeTest.ecocow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MOVIE_NOT_FOUND(404, "영화를 찾을 수 없습니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
