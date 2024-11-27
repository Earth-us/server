package com.greenity.server.service;


import com.greenity.server.model.Activity;
import com.greenity.server.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }
    public List<Activity> searchActivities(Boolean isEnd, Activity.Category category, Activity.ActivityMethod activityMethod) {
        return activityRepository.findActivities(isEnd, category, activityMethod);
    }

}