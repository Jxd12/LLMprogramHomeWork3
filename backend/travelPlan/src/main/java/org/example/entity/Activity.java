// src/main/java/org/example/entity/Activity.java
package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_itinerary_id")
    private DailyItinerary dailyItinerary;

    @Column(name = "activity_type")
    private String activityType; // 'breakfast', 'morning', 'lunch', 'afternoon', 'dinner', 'evening'

    @Column(name = "place")
    private String place;

    @Column(name = "budget")
    private String budget;

    @Column(name = "content")
    private String content;

    @Column(name = "transport")
    private String transport;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 构造函数
    public Activity() {}

    // Getter和Setter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DailyItinerary getDailyItinerary() {
        return dailyItinerary;
    }

    public void setDailyItinerary(DailyItinerary dailyItinerary) {
        this.dailyItinerary = dailyItinerary;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
