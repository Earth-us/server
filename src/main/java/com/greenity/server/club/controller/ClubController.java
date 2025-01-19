package com.greenity.server.club.controller;

import com.greenity.server.club.dto.CreateClubRequest;
import com.greenity.server.club.service.ClubServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
@Tag(name = "모임 API", description = "모임 관련 API")
@RequiredArgsConstructor
@Validated
public class ClubController {

    private final ClubServiceImpl clubService;

    @PostMapping
    public ResponseEntity<Void> createClub(
            @Valid  @RequestBody CreateClubRequest createClubRequest,
            Long userId
    ){
        clubService.createClub(createClubRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
