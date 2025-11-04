// src/main/java/org/example/entity/DailyItinerary.java
package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "daily_itineraries")
public class DailyItinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_plan_id")
    private TravelPlan travelPlan;

    @Column(name = "day_number")
    private Integer dayNumber;

    // 住宿信息字段
    @Column(name = "accommodation_place")
    private String accommodationPlace;

    @Column(name = "accommodation_budget")
    private String accommodationBudget;

    @Column(name = "accommodation_content")
    private String accommodationContent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "dailyItinerary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Activity> activities;

    // 构造函数
    public DailyItinerary() {}

    // Getter和Setter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TravelPlan getTravelPlan() {
        return travelPlan;
    }

    public void setTravelPlan(TravelPlan travelPlan) {
        this.travelPlan = travelPlan;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
