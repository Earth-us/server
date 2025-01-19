package com.greenity.server.user.dto.response;

import com.greenity.server.badge.model.Badge;
import com.greenity.server.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserResponse {
    private String name;
    private String phone;
    private String nickname;
    private String email;
    private Long activity_count;
    private List<Badge> badges;


    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .phone(user.getPhone())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .activity_count(user.getActivityCount())
                .badges(user.getBadges())
                .build();
    }
}
