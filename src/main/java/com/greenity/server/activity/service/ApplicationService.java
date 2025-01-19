package com.greenity.server.activity.service;

import com.greenity.server.activity.dto.ApplicationDTO;
import com.greenity.server.activity.mapper.JoinActivityMapper;
import com.greenity.server.activity.entity.JoinActivity;
import com.greenity.server.activity.repository.JoinActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {
    private final JoinActivityRepository joinActivityRepository;

    public ApplicationService(JoinActivityRepository joinActivityRepository) {
        this.joinActivityRepository = joinActivityRepository;
    }

    /*
    신청 정보 확인
     */
public List<ApplicationDTO> getApplicationsByUserId(Long userId) {
    //신청 데이터 조회
    List<JoinActivity> applications = joinActivityRepository.findByUserId(userId);
    List<ApplicationDTO> applicationDTOS = new ArrayList<>();

    //DTO로 변환
    for(JoinActivity application : applications) {
        ApplicationDTO dto = JoinActivityMapper.toDTO(application);
        applicationDTOS.add(dto);

    }

    return applicationDTOS;
    }
}
