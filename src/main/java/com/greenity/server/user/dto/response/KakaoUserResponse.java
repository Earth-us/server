package com.greenity.server.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoUserResponse {
    private Long id;
    private String nickname;
    private String email;
    private String image;
}
