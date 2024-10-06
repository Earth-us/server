package com.greenity.server.community.service;

import com.greenity.server.community.domain.Writing;
import com.greenity.server.community.repository.WritingRepository;
import com.greenity.server.user.domain.User;
import com.greenity.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityWritingService {

    private final WritingRepository writingRepository;
    private final UserRepository userRepository;

    public Long write(Long userId, String title, String content, String writing_pic) {
        User writer = userRepository.findById(userId).orElse(null);
        Writing writing = Writing.builder()
                .title(title)
                .content(content)
                .writing_pic(writing_pic)
                .heartCount(0L)
                .writer(writer)
                .build();
        writingRepository.save(writing);
        return writing.getId();
    }

    public void editWriting(Long writingId, String title, String content, String writing_pic) {
        Writing writing = writingRepository.findById(writingId).orElse(null);
        writing.setTitle(title);
        writing.setContent(content);
        writing.setWriting_pic(writing_pic);
        writingRepository.save(writing);
    }

    public void deleteWriting(Long writingId) {
        Writing writing = writingRepository.findById(writingId).orElse(null);
        writingRepository.delete(writing);
    }

}
