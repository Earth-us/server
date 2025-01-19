package com.greenity.server.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private String password;
}
