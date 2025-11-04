// src/main/java/org/example/controller/TravelPlanController.java
package org.example.controller;

import org.example.dto.TravelPlanDTO;
import org.example.dto.DailyItineraryDTO;
import org.example.dto.ActivityDTO;
import org.example.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TravelPlanController {

    private final TravelPlanService travelPlanService;

    @Autowired
    public TravelPlanController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    // ==================== TravelPlan 相关接口 ====================

    /**
     * 查询所有旅行计划
     */
    @GetMapping("/travel-plans")
    public ResponseEntity<List<TravelPlanDTO>> getAllTravelPlans() {
        List<TravelPlanDTO> travelPlans = travelPlanService.getAllTravelPlans();
        return ResponseEntity.ok(travelPlans);
    }

    /**
     * 根据ID查询旅行计划
     */
    @GetMapping("/travel-plans/{id}")
    public ResponseEntity<TravelPlanDTO> getTravelPlanById(@PathVariable Long id) {
        TravelPlanDTO travelPlan = travelPlanService.getTravelPlanById(id);
        if (travelPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(travelPlan);
    }

    /**
     * 根据用户ID查询旅行计划
     */
    @GetMapping("/travel-plans/user/{userId}")
    public ResponseEntity<List<TravelPlanDTO>> getTravelPlansByUserId(@PathVariable Long userId) {
        List<TravelPlanDTO> travelPlans = travelPlanService.getTravelPlansByUserId(userId);
        return ResponseEntity.ok(travelPlans);
    }

    /**
     * 创建新的旅行计划
     */
    @PostMapping("/travel-plans")
    public ResponseEntity<TravelPlanDTO> createTravelPlan(@RequestBody TravelPlanDTO travelPlanDTO) {
        TravelPlanDTO createdTravelPlan = travelPlanService.createTravelPlan(travelPlanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTravelPlan);
    }

    /**
     * 更新旅行计划
     */
    @PutMapping("/travel-plans/{id}")
    public ResponseEntity<TravelPlanDTO> updateTravelPlan(@PathVariable Long id,
                                                          @RequestBody TravelPlanDTO travelPlanDTO) {
        TravelPlanDTO updatedTravelPlan = travelPlanService.updateTravelPlan(id, travelPlanDTO);
        if (updatedTravelPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTravelPlan);
    }

    /**
     * 删除旅行计划
     */
    @DeleteMapping("/travel-plans/{id}")
    public ResponseEntity<Void> deleteTravelPlan(@PathVariable Long id) {
        travelPlanService.deleteTravelPlan(id);
        return ResponseEntity.noContent().build();
    }

    // ==================== DailyItinerary 相关接口 ====================

    /**
     * 根据旅行计划ID查询所有每日行程
     */
    @GetMapping("/travel-plans/{travelPlanId}/itineraries")
    public ResponseEntity<List<DailyItineraryDTO>> getDailyItinerariesByTravelPlanId(@PathVariable Long travelPlanId) {
        List<DailyItineraryDTO> itineraries = travelPlanService.getDailyItinerariesByTravelPlanId(travelPlanId);
        return ResponseEntity.ok(itineraries);
    }

    /**
     * 创建新的每日行程
     */
    @PostMapping("/travel-plans/{travelPlanId}/itineraries")
    public ResponseEntity<DailyItineraryDTO> createDailyItinerary(@PathVariable Long travelPlanId,
                                                                  @RequestBody DailyItineraryDTO dailyItineraryDTO) {
        DailyItineraryDTO createdItinerary = travelPlanService.createDailyItinerary(dailyItineraryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItinerary);
    }

    /**
     * 更新每日行程
     */
    @PutMapping("/itineraries/{id}")
    public ResponseEntity<DailyItineraryDTO> updateDailyItinerary(@PathVariable Long id,
                                                                  @RequestBody DailyItineraryDTO dailyItineraryDTO) {
        DailyItineraryDTO updatedItinerary = travelPlanService.updateDailyItinerary(id, dailyItineraryDTO);
        if (updatedItinerary == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedItinerary);
    }

    /**
     * 删除每日行程
     */
    @DeleteMapping("/itineraries/{id}")
    public ResponseEntity<Void> deleteDailyItinerary(@PathVariable Long id) {
        travelPlanService.deleteDailyItinerary(id);
        return ResponseEntity.noContent().build();
    }

    // ==================== Activity 相关接口 ====================

    /**
     * 根据每日行程ID查询所有活动
     */
    @GetMapping("/itineraries/{dailyItineraryId}/activities")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByDailyItineraryId(@PathVariable Long dailyItineraryId) {
        List<ActivityDTO> activities = travelPlanService.getActivitiesByDailyItineraryId(dailyItineraryId);
        return ResponseEntity.ok(activities);
    }

    /**
     * 创建新的活动
     */
    @PostMapping("/itineraries/{dailyItineraryId}/activities")
    public ResponseEntity<ActivityDTO> createActivity(@PathVariable Long dailyItineraryId,
                                                      @RequestBody ActivityDTO activityDTO) {
        ActivityDTO createdActivity = travelPlanService.createActivity(activityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }

    /**
     * 更新活动
     */
    @PutMapping("/activities/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long id,
                                                      @RequestBody ActivityDTO activityDTO) {
        ActivityDTO updatedActivity = travelPlanService.updateActivity(id, activityDTO);
        if (updatedActivity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedActivity);
    }

    /**
     * 删除活动
     */
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        travelPlanService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
