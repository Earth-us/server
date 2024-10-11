package com.greenity.server.community.controller;

import com.greenity.server.community.domain.Writing;
import com.greenity.server.community.dto.request.SearchRequest;
import com.greenity.server.community.dto.response.CommentResponse;
import com.greenity.server.community.dto.response.WritingDetailResponse;
import com.greenity.server.community.dto.response.WritingResponse;
import com.greenity.server.community.dto.response.WritingResponseList;
import com.greenity.server.community.service.CommunityMainService;
import com.greenity.server.global.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityMainController {

    private final CommunityMainService communityMainService;

    @GetMapping("/community") //전체 글 불러오기
    @ResponseBody
    public ResponseTemplate<?> allWritings() {
        List<WritingResponse> writings = communityMainService.allWritings();
        return ResponseTemplate.from(WritingResponseList.from(writings));
    }

    @GetMapping("/community/{writingId}/detail") //글 상세보기
    @ResponseBody
    public ResponseTemplate<?> showDetail(@PathVariable Long writingId) {
        WritingResponse writing = WritingResponse.from(communityMainService.showWriting(writingId));
        List<CommentResponse> commentList = communityMainService.showCommentList(writingId);
        return ResponseTemplate.from(WritingDetailResponse.from(writing, commentList));
    }

    @GetMapping("/community/search") //글 검색
    @ResponseBody
    public ResponseTemplate<?> searchWritings(SearchRequest searchRequest) {
        List<WritingResponse> writings = communityMainService.searchWritings(searchRequest.nickname(), searchRequest.title(), searchRequest.content());
        return ResponseTemplate.from(WritingResponseList.from(writings));
    }

}
