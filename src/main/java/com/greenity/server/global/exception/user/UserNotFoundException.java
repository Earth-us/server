package com.greenity.server.global.exception.user;

import com.greenity.server.global.exception.CustomException;
import com.greenity.server.global.exception.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
