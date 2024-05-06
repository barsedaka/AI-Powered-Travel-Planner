package com.bar.travelplanner.repository;

import com.bar.travelplanner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    @Query("""
        SELECT itinerary
        FROM Itinerary itinerary
        WHERE itinerary.user.id = :userId
        """)
    List<Itinerary> findAllItinerariesByUserId(Long userId);
}
