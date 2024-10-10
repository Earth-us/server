package com.greenity.server.community.dto.response;

import java.util.List;

public record MyWritingResponseList(
        List<MyWritingResponse> myWritingResponseList
) {
    public static MyWritingResponseList from(List<MyWritingResponse> myWritingResponseList) {
        return new MyWritingResponseList(myWritingResponseList);
    }
}
