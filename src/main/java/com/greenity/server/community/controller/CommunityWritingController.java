package com.greenity.server.community.controller;

import com.greenity.server.community.dto.request.WritingRequest;
import com.greenity.server.community.service.CommunityWritingService;
import com.greenity.server.global.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommunityWritingController {

    private final CommunityWritingService writingService;

    @PostMapping("/community/writing")  //글 작성
    @ResponseBody
    public ResponseTemplate<?> write(Long userId, @RequestBody WritingRequest writingRequest) {
        Long writingId = writingService.write(userId, writingRequest.title(), writingRequest.content(), writingRequest.writing_pic());
        return ResponseTemplate.from(writingId);
    }

    @PostMapping("/community/writing/{writingId}")   //글 수정
    @ResponseBody
    public ResponseTemplate<?> editWriting(@PathVariable Long writingId, @RequestBody WritingRequest writingRequest) {
        writingService.editWriting(writingId, writingRequest.title(), writingRequest.content(), writingRequest.writing_pic());
        return ResponseTemplate.EMPTY_RESPONSE;
    }

    @DeleteMapping("/community/writing/{writingId}")  //글 삭제
    @ResponseBody
    public ResponseTemplate<?> deleteWriting(@PathVariable Long writingId) {
        writingService.deleteWriting(writingId);
        return ResponseTemplate.EMPTY_RESPONSE;
    }

}
