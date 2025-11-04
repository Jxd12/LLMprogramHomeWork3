// src/main/java/org/example/service/TravelPlanService.java
package org.example.service;

import org.example.dto.TravelPlanDTO;
import org.example.dto.DailyItineraryDTO;
import org.example.dto.ActivityDTO;
import java.util.List;

public interface TravelPlanService {
    List<TravelPlanDTO> getAllTravelPlans();
    TravelPlanDTO getTravelPlanById(Long id);
    List<TravelPlanDTO> getTravelPlansByUserId(Long userId);
    TravelPlanDTO createTravelPlan(TravelPlanDTO travelPlanDTO);
    TravelPlanDTO updateTravelPlan(Long id, TravelPlanDTO travelPlanDTO);
    void deleteTravelPlan(Long id);

    // DailyItinerary相关方法
    List<DailyItineraryDTO> getDailyItinerariesByTravelPlanId(Long travelPlanId);
    DailyItineraryDTO createDailyItinerary(DailyItineraryDTO dailyItineraryDTO);
    DailyItineraryDTO updateDailyItinerary(Long id, DailyItineraryDTO dailyItineraryDTO);
    void deleteDailyItinerary(Long id);

    // Activity相关方法
    List<ActivityDTO> getActivitiesByDailyItineraryId(Long dailyItineraryId);
    ActivityDTO createActivity(ActivityDTO activityDTO);
    ActivityDTO updateActivity(Long id, ActivityDTO activityDTO);
    void deleteActivity(Long id);
}
