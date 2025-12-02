package com.lostfinder.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 공통
    INVALID_INPUT(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 오류가 발생했습니다."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "회원 정보를 찾을 수 없습니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT.value(), "이미 존재하는 아이디입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT.value(), "이미 존재하는 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED.value(), "비밀번호가 일치하지 않습니다."),

    // Post
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "게시글을 찾을 수 없습니다.");

    private final int status;
    private final String message;
}
