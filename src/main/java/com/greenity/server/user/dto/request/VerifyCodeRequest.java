package com.greenity.server.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyCodeRequest {
    private String email;
    private String code;
}
