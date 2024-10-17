package com.greenity.server.chat.exception.errorcode;

import com.greenity.server.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatErrorCode implements ErrorCode {
    CHAT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 채팅을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
