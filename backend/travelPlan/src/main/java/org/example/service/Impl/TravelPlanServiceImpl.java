// src/main/java/org/example/service/Impl/TravelPlanServiceImpl.java
package org.example.service.Impl;

import org.example.dto.TravelPlanDTO;
import org.example.dto.DailyItineraryDTO;
import org.example.dto.ActivityDTO;
import org.example.dto.DTOConverter;
import org.example.entity.TravelPlan;
import org.example.entity.DailyItinerary;
import org.example.entity.Activity;
import org.example.repository.TravelPlanRepository;
import org.example.repository.DailyItineraryRepository;
import org.example.repository.ActivityRepository;
import org.example.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TravelPlanServiceImpl implements TravelPlanService {

    @Autowired
    private TravelPlanRepository travelPlanRepository;

    @Autowired
    private DailyItineraryRepository dailyItineraryRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TravelPlanDTO> getAllTravelPlans() {
        List<TravelPlan> travelPlans = travelPlanRepository.findAll();
        return travelPlans.stream()
                .map(DTOConverter::convertToTravelPlanDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TravelPlanDTO getTravelPlanById(Long id) {
        return travelPlanRepository.findById(id)
                .map(DTOConverter::convertToTravelPlanDTO)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelPlanDTO> getTravelPlansByUserId(Long userId) {
        List<TravelPlan> travelPlans = travelPlanRepository.findByUserId(userId);
        return travelPlans.stream()
                .map(DTOConverter::convertToTravelPlanDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TravelPlanDTO createTravelPlan(TravelPlanDTO travelPlanDTO) {
        TravelPlan travelPlan = DTOConverter.convertToTravelPlan(travelPlanDTO);
        travelPlan.setCreatedAt(LocalDateTime.now());
        travelPlan.setUpdatedAt(LocalDateTime.now());
        TravelPlan savedTravelPlan = travelPlanRepository.save(travelPlan);
        return DTOConverter.convertToTravelPlanDTO(savedTravelPlan);
    }

    @Override
    @Transactional
    public TravelPlanDTO updateTravelPlan(Long id, TravelPlanDTO travelPlanDTO) {
        return travelPlanRepository.findById(id).map(existingPlan -> {
            existingPlan.setDestination(travelPlanDTO.getDestination());
            existingPlan.setDuration(travelPlanDTO.getDuration());
            existingPlan.setBudget(travelPlanDTO.getBudget());
            existingPlan.setCompanions(travelPlanDTO.getCompanions());
            existingPlan.setPreferences(travelPlanDTO.getPreferences());
            existingPlan.setUpdatedAt(LocalDateTime.now());
            TravelPlan updatedTravelPlan = travelPlanRepository.save(existingPlan);
            return DTOConverter.convertToTravelPlanDTO(updatedTravelPlan);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void deleteTravelPlan(Long id) {
        travelPlanRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DailyItineraryDTO> getDailyItinerariesByTravelPlanId(Long travelPlanId) {
        List<DailyItinerary> dailyItineraries = dailyItineraryRepository.findByTravelPlanId(travelPlanId);
        return dailyItineraries.stream()
                .map(DTOConverter::convertToDailyItineraryDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DailyItineraryDTO createDailyItinerary(DailyItineraryDTO dailyItineraryDTO) {
        DailyItinerary dailyItinerary = DTOConverter.convertToDailyItinerary(dailyItineraryDTO);
        dailyItinerary.setCreatedAt(LocalDateTime.now());
        DailyItinerary savedDailyItinerary = dailyItineraryRepository.save(dailyItinerary);
        return DTOConverter.convertToDailyItineraryDTO(savedDailyItinerary);
    }

    @Override
    @Transactional
    public DailyItineraryDTO updateDailyItinerary(Long id, DailyItineraryDTO dailyItineraryDTO) {
        return dailyItineraryRepository.findById(id).map(existingItinerary -> {
            existingItinerary.setDayNumber(dailyItineraryDTO.getDayNumber());
            existingItinerary.setAccommodationPlace(dailyItineraryDTO.getAccommodationPlace());
            existingItinerary.setAccommodationBudget(dailyItineraryDTO.getAccommodationBudget());
            existingItinerary.setAccommodationContent(dailyItineraryDTO.getAccommodationContent());
            DailyItinerary updatedDailyItinerary = dailyItineraryRepository.save(existingItinerary);
            return DTOConverter.convertToDailyItineraryDTO(updatedDailyItinerary);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void deleteDailyItinerary(Long id) {
        dailyItineraryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityDTO> getActivitiesByDailyItineraryId(Long dailyItineraryId) {
        List<Activity> activities = activityRepository.findByDailyItineraryId(dailyItineraryId);
        return activities.stream()
                .map(DTOConverter::convertToActivityDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        Activity activity = DTOConverter.convertToActivity(activityDTO);
        activity.setCreatedAt(LocalDateTime.now());
        Activity savedActivity = activityRepository.save(activity);
        return DTOConverter.convertToActivityDTO(savedActivity);
    }

    @Override
    @Transactional
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        return activityRepository.findById(id).map(existingActivity -> {
            existingActivity.setActivityType(activityDTO.getActivityType());
            existingActivity.setPlace(activityDTO.getPlace());
            existingActivity.setBudget(activityDTO.getBudget());
            existingActivity.setContent(activityDTO.getContent());
            existingActivity.setTransport(activityDTO.getTransport());
            Activity updatedActivity = activityRepository.save(existingActivity);
            return DTOConverter.convertToActivityDTO(updatedActivity);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
