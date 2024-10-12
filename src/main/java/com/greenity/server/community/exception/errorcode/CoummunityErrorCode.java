package com.greenity.server.community.exception.errorcode;

import com.greenity.server.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CoummunityErrorCode implements ErrorCode {
    WRITING_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 댓글을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}