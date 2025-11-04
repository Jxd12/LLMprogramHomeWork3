package org.example.repository;// src/main/java/org/example/model/TravelPlan.java

// src/main/java/org/example/repository/DailyItineraryRepository.java

import org.example.entity.DailyItinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DailyItineraryRepository extends JpaRepository<DailyItinerary, Long> {
    List<DailyItinerary> findByTravelPlanId(Long travelPlanId);
}
