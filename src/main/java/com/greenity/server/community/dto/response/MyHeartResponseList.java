package com.greenity.server.community.dto.response;

import java.util.List;

public record MyHeartResponseList(
        List<MyHeartResponse> myHeartResponseList
) {
    public static MyHeartResponseList from(List<MyHeartResponse> myHeartResponseList) {
        return new MyHeartResponseList(myHeartResponseList);
    }
}
