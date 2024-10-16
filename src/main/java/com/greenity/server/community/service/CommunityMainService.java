package com.greenity.server.community.service;

import com.greenity.server.community.domain.Writing;
import com.greenity.server.community.dto.response.CommentResponse;
import com.greenity.server.community.dto.response.WritingResponse;
import com.greenity.server.community.exception.WritingNotFoundException;
import com.greenity.server.community.exception.errorcode.CoummunityErrorCode;
import com.greenity.server.community.repository.CommentRepository;
import com.greenity.server.community.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityMainService {

    private final WritingRepository writingRepository;
    private final CommentRepository commentRepository;

    public List<WritingResponse> allWritings() {
        return writingRepository.findAllByOrderByIdDesc().stream()
                .map(WritingResponse::from)
                .toList();
    }

    public List<WritingResponse> searchWritings(String nickname, String title, String content) {
        return writingRepository.searchWritings(nickname, title, content).stream()
                .map(WritingResponse::from)
                .toList();
    }

    public Writing showWriting(Long writingId) {
        return writingRepository.findById(writingId)
                .orElseThrow(() -> new WritingNotFoundException(CoummunityErrorCode.WRITING_NOT_FOUND));
    }

    public List<CommentResponse> showCommentList(Long writingId) {
        return commentRepository.findAllByWritingId(writingId).stream()
                .map(CommentResponse::from)
                .toList();
    }

}
