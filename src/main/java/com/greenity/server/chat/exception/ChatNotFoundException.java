package com.greenity.server.chat.exception;

import com.greenity.server.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
}
