package com.greenity.server.community.controller;

import com.greenity.server.community.dto.response.*;
import com.greenity.server.community.service.CommunityUserService;
import com.greenity.server.global.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityUserController {

    private final CommunityUserService communityUserService;

    @GetMapping("/community/writings")
    @ResponseBody
    public ResponseTemplate<?> myWritings(Long userId) {
        List<MyWritingResponse> writings = communityUserService.myWritings(userId);
        return ResponseTemplate.from(MyWritingResponseList.from(writings));
    }

    @GetMapping("/community/comments")
    @ResponseBody
    public ResponseTemplate<?> myComments(Long userId) {
        List<MyCommentResponse> comments = communityUserService.myComments(userId);
        return ResponseTemplate.from(MyCommentResponseList.from(comments));
    }

    @GetMapping("/community/hearts")
    @ResponseBody
    public ResponseTemplate<?> myHearts(Long userId) {
        List<MyHeartResponse> hearts = communityUserService.myHearts(userId);
        return ResponseTemplate.from(MyHeartResponseList.from(hearts));
    }

}
