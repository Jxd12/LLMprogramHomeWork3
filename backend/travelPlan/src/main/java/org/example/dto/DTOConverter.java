package org.example.dto;

import org.example.entity.Activity;
import org.example.entity.DailyItinerary;
import org.example.entity.TravelPlan;

import java.util.List;
import java.util.stream.Collectors;

// org.example.dto.DTOConverter.java
public class DTOConverter {
    
    public static TravelPlanDTO convertToTravelPlanDTO(TravelPlan travelPlan) {
        if (travelPlan == null) return null;
        
        TravelPlanDTO dto = new TravelPlanDTO();
        dto.setId(travelPlan.getId());
        dto.setUserId(travelPlan.getUserId());
        dto.setDestination(travelPlan.getDestination());
        dto.setDuration(travelPlan.getDuration());
        dto.setBudget(travelPlan.getBudget());
        dto.setCompanions(travelPlan.getCompanions());
        dto.setPreferences(travelPlan.getPreferences());
        
        if (travelPlan.getDailyItineraries() != null) {
            List<DailyItineraryDTO> itineraryDTOs = travelPlan.getDailyItineraries()
                .stream()
                .map(DTOConverter::convertToDailyItineraryDTO)
                .collect(Collectors.toList());
            dto.setDailyItineraries(itineraryDTOs);
        }
        
        return dto;
    }
    
    public static DailyItineraryDTO convertToDailyItineraryDTO(DailyItinerary dailyItinerary) {
        if (dailyItinerary == null) return null;
        
        DailyItineraryDTO dto = new DailyItineraryDTO();
        dto.setId(dailyItinerary.getId());
        dto.setDayNumber(dailyItinerary.getDayNumber());
        dto.setAccommodationPlace(dailyItinerary.getAccommodationPlace());
        dto.setAccommodationBudget(dailyItinerary.getAccommodationBudget());
        dto.setAccommodationContent(dailyItinerary.getAccommodationContent());
        
        if (dailyItinerary.getActivities() != null) {
            List<ActivityDTO> activityDTOs = dailyItinerary.getActivities()
                .stream()
                .map(DTOConverter::convertToActivityDTO)
                .collect(Collectors.toList());
            dto.setActivities(activityDTOs);
        }
        
        return dto;
    }
    
    public static ActivityDTO convertToActivityDTO(Activity activity) {
        if (activity == null) return null;
        
        ActivityDTO dto = new ActivityDTO();
        dto.setId(activity.getId());
        dto.setActivityType(activity.getActivityType());
        dto.setPlace(activity.getPlace());
        dto.setBudget(activity.getBudget());
        dto.setContent(activity.getContent());
        dto.setTransport(activity.getTransport());
        
        return dto;
    }
    
    public static TravelPlan convertToTravelPlan(TravelPlanDTO dto) {
        if (dto == null) return null;
        
        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setId(dto.getId());
        travelPlan.setUserId(dto.getUserId());
        travelPlan.setDestination(dto.getDestination());
        travelPlan.setDuration(dto.getDuration());
        travelPlan.setBudget(dto.getBudget());
        travelPlan.setCompanions(dto.getCompanions());
        travelPlan.setPreferences(dto.getPreferences());
        
        return travelPlan;
    }
    
    public static DailyItinerary convertToDailyItinerary(DailyItineraryDTO dto) {
        if (dto == null) return null;
        
        DailyItinerary dailyItinerary = new DailyItinerary();
        dailyItinerary.setId(dto.getId());
        dailyItinerary.setDayNumber(dto.getDayNumber());
        dailyItinerary.setAccommodationPlace(dto.getAccommodationPlace());
        dailyItinerary.setAccommodationBudget(dto.getAccommodationBudget());
        dailyItinerary.setAccommodationContent(dto.getAccommodationContent());
        
        return dailyItinerary;
    }
    
    public static Activity convertToActivity(ActivityDTO dto) {
        if (dto == null) return null;
        
        Activity activity = new Activity();
        activity.setId(dto.getId());
        activity.setActivityType(dto.getActivityType());
        activity.setPlace(dto.getPlace());
        activity.setBudget(dto.getBudget());
        activity.setContent(dto.getContent());
        activity.setTransport(dto.getTransport());
        
        return activity;
    }
}
