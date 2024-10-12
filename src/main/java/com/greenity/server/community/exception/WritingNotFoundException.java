package com.greenity.server.community.exception;

import com.greenity.server.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WritingNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
}