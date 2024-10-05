package com.greenity.server.community.controller;

import com.greenity.server.community.dto.request.CommentRequest;
import com.greenity.server.community.service.CommunityReactionService;
import com.greenity.server.global.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommunityReactionController {

    private final CommunityReactionService reactionService;

    @PostMapping("/community/comment/{writingId}")  //댓글 작성
    @ResponseBody
    public ResponseTemplate<?> writeComment(Long userId, @PathVariable Long writingId, @RequestBody CommentRequest commentRequest) {
        reactionService.writeComment(userId, writingId, commentRequest.content());
        return ResponseTemplate.EMPTY_RESPONSE;
    }

    @PostMapping("/community/comment/{commentId}")  //댓글 수정
    @ResponseBody
    public ResponseTemplate<?> editComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        reactionService.editComment(commentId, commentRequest.content());
        return ResponseTemplate.EMPTY_RESPONSE;
    }

    @DeleteMapping("/community/comment/{commentId}") //댓글 삭제
    @ResponseBody
    public ResponseTemplate<?> deleteComment(@PathVariable Long commentId) {
        reactionService.deleteComment(commentId);
        return ResponseTemplate.EMPTY_RESPONSE;
    }

    @PostMapping("/community/heart/{writingId}") //하트 누르기
    @ResponseBody
    public ResponseTemplate<?> heart(Long userId, @PathVariable Long writingId) {
        reactionService.heart(userId, writingId);
        return ResponseTemplate.EMPTY_RESPONSE;
    }

    @DeleteMapping("/community/heart/{writingId}")  //하트 취소
    @ResponseBody
    public ResponseTemplate<?> cancelHeart(Long userId, @PathVariable Long writingId) {
        reactionService.cancelHeart(userId, writingId);
        return ResponseTemplate.EMPTY_RESPONSE;
    }

}
