package com.greenity.server.calendar.service;

import com.greenity.server.activity.model.ActivityRound;
import com.greenity.server.activity.repository.ActivityRoundRepository;
import com.greenity.server.calendar.dto.CalendarActivityResponse;
import com.greenity.server.user.model.User;
import com.greenity.server.user.repository.UserRepository;
import com.greenity.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final ActivityRoundRepository activityRoundRepository;;
    private final UserRepository userRepository;

    public List<CalendarActivityResponse> getUserScheduleActivity(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("등록된 사용자가 아닙니다."));

        List<ActivityRound> activities = activityRoundRepository.findActivityRoundsByUserId(user.getId());

        return activities.stream()
                .map(activityRound -> CalendarActivityResponse.builder()
                        .title(activityRound.getActivity().getTitle())
                        .activityDate(activityRound.getActivityDate())
                        .activityTime(activityRound.getActivityTime())
                        .activityNum(activityRound.getActivityNum()).build())
                .collect(Collectors.toList());
    }
}
