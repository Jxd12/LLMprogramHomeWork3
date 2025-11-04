// src/main/java/org/example/repository/ActivityRepository.java
package org.example.repository;

import org.example.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByDailyItineraryId(Long dailyItineraryId);
    List<Activity> findByDailyItineraryIdAndActivityType(Long dailyItineraryId, String activityType);
}
