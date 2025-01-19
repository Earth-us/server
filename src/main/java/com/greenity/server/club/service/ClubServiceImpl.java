package com.greenity.server.club.service;

import com.greenity.server.club.dto.CreateClubRequest;
import com.greenity.server.club.entity.Club;
import com.greenity.server.club.entity.Member;
import com.greenity.server.club.entity.Role;
import com.greenity.server.club.exception.InsufficientActivityException;
import com.greenity.server.club.repository.ClubRepository;
import com.greenity.server.club.repository.MemberRepository;
import com.greenity.server.global.exception.user.UserNotFoundException;
import com.greenity.server.user.entity.User;
import com.greenity.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;



    //생성할 자격이 있는지 검사
    public void validCreateClub(Long userId) {
        //활동횟수 조회
        User user  = userRepository.findById(userId)
        .orElseThrow(()-> new UserNotFoundException());

        //활동 횟수 조회
        if (user.getActivityCount()<10){
        throw new InsufficientActivityException();
             }

    }

    //모임 등록
    @Transactional
    public void createClub(
            CreateClubRequest request,
            Long userId
                    ) {

        //사용자 검증 ->어노테이션으로

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException());

        //모임 생성
        Club club = request.toEntity();
        clubRepository.save(club);

        //멤버 테이블에 리더 생성
        Member member = Member.builder()
                .user(user)
                .club(club)
                .role(Role.LEADER)
                .build();

        memberRepository.save(member);

    }





}
