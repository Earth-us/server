package com.greenity.server.community.controller;

import com.greenity.server.community.dto.request.SearchRequest;
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

    @GetMapping("/community")
    @ResponseBody
    public ResponseTemplate<?> allWritings() {
        List<WritingResponse> writings = communityMainService.allWritings();
        return ResponseTemplate.from(WritingResponseList.from(writings));
    }

    @GetMapping("/community/search")
    @ResponseBody
    public ResponseTemplate<?> searchWritings(SearchRequest searchRequest) {
        List<WritingResponse> writings = communityMainService.searchWritings(searchRequest.keyword());
        return ResponseTemplate.from(WritingResponseList.from(writings));
    }

}
