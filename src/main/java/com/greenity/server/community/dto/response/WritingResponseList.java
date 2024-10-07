package com.greenity.server.community.dto.response;

import java.util.List;

public record WritingResponseList(
        List<WritingResponse> writingResponseList
) {
    public static WritingResponseList from(List<WritingResponse> writingResponseList) {
        return new WritingResponseList(writingResponseList);
    }
}
