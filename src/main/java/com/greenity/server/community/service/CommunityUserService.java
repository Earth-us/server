package com.greenity.server.community.service;

import com.greenity.server.community.dto.response.MyCommentResponse;
import com.greenity.server.community.dto.response.MyHeartResponse;
import com.greenity.server.community.dto.response.MyWritingResponse;
import com.greenity.server.community.repository.CommentRepository;
import com.greenity.server.community.repository.HeartRepository;
import com.greenity.server.community.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityUserService {

    private final WritingRepository writingRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;

    public List<MyWritingResponse> myWritings(Long userId) {
        return writingRepository.findAllByUserId(userId).stream()
                .map(MyWritingResponse::from)
                .toList();
    }

    public List<MyCommentResponse> myComments(Long userId) {
        return commentRepository.findAllByUserId(userId).stream()
                .map(MyCommentResponse::from)
                .toList();
    }

    public List<MyHeartResponse> myHearts(Long userId) {
        return heartRepository.findAllByUserId(userId).stream()
                .map(MyHeartResponse::from)
                .toList();
    }

}
