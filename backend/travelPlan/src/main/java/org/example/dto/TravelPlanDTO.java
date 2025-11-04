package org.example.dto;

import java.util.List;

public class TravelPlanDTO {
    private Long id;
    private Long userId;
    private String destination;
    private String duration;
    private String budget;
    private String companions;
    private String preferences;
    private List<DailyItineraryDTO> dailyItineraries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCompanions() {
        return companions;
    }

    public void setCompanions(String companions) {
        this.companions = companions;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public List<DailyItineraryDTO> getDailyItineraries() {
        return dailyItineraries;
    }

    public void setDailyItineraries(List<DailyItineraryDTO> dailyItineraries) {
        this.dailyItineraries = dailyItineraries;
    }
}
