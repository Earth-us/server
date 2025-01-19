package com.greenity.server.club.exception;

import com.greenity.server.global.exception.CustomException;
import com.greenity.server.global.exception.ErrorCode;

public class InsufficientActivityException extends CustomException {
    public InsufficientActivityException() {

        super(ErrorCode.INSUFFICIENT_ACTIVITY);
    }
}
