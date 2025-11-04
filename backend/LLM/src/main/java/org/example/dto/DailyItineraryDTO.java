package org.example.dto;


import java.util.List;

public class DailyItineraryDTO {
    private Long id;
    private Integer dayNumber;
    private String accommodationPlace;
    private String accommodationBudget;
    private String accommodationContent;
    private List<ActivityDTO> activities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getAccommodationPlace() {
        return accommodationPlace;
    }

    public void setAccommodationPlace(String accommodationPlace) {
        this.accommodationPlace = accommodationPlace;
    }

    public String getAccommodationBudget() {
        return accommodationBudget;
    }

    public void setAccommodationBudget(String accommodationBudget) {
        this.accommodationBudget = accommodationBudget;
    }

    public String getAccommodationContent() {
        return accommodationContent;
    }

    public void setAccommodationContent(String accommodationContent) {
        this.accommodationContent = accommodationContent;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }
}
