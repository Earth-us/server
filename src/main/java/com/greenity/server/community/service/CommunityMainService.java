package com.greenity.server.community.service;

import com.greenity.server.community.dto.response.WritingResponse;
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

    public List<WritingResponse> searchWritings(String keyword) {
        return writingRepository.searchWritings(keyword).stream()
                .map(WritingResponse::from)
                .toList();
    }

}
