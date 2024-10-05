package com.greenity.server.community.service;

import com.greenity.server.community.domain.Comment;
import com.greenity.server.community.domain.Heart;
import com.greenity.server.community.domain.Writing;
import com.greenity.server.community.repository.CommentRepository;
import com.greenity.server.community.repository.HeartRepository;
import com.greenity.server.community.repository.WritingRepository;
import com.greenity.server.user.domain.User;
import com.greenity.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityReactionService {

    private final WritingRepository writingRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    private final UserRepository userRepository;

    public void writeComment(Long userId, Long writingId, String content) {
        Writing originalWriting = writingRepository.findById(writingId).orElse(null);
        User commenter = userRepository.findById(userId).orElse(null);
        Comment comment = Comment.builder()
                .content(content)
                .commenter(commenter)
                .originalWriting(originalWriting)
                .build();
        commentRepository.save(comment);
    }

    public void editComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        comment.setContent(content);
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        commentRepository.delete(comment);
    }

    public void heart(Long userId, Long writingId) {
        User heartUser = userRepository.findById(userId).orElse(null);
        Writing originalWriting = writingRepository.findById(writingId).orElse(null);
        writingRepository.upHeartCount(writingId);
        Heart heart = Heart.builder()
                .heartUser(heartUser)
                .originalWriting(originalWriting)
                .build();
        heartRepository.save(heart);
    }

    public void cancelHeart(Long userId, Long writingId) {
        Heart heart = heartRepository.findHeartByUserAndWriting(userId, writingId);
        writingRepository.downHeartCount(writingId);
        heartRepository.delete(heart);
    }

}
