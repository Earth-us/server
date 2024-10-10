package com.greenity.server.community.dto.response;

import java.util.List;

public record MyCommentResponseList(
        List<MyCommentResponse> myCommentResponseList
) {
    public static MyCommentResponseList from(List<MyCommentResponse> myCommentResponseList) {
        return new MyCommentResponseList(myCommentResponseList);
    }
}
