package com.bar.travelplanner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Itinerary")
@Table(name = "itinerary")
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destination;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "activity_types", joinColumns = @JoinColumn(name = "itinerary_id"))
    @Column(name = "activity_type", nullable = false)
    private List<String> activityTypes = new ArrayList<>();

    @Column(name = "trip_duration", nullable = false)
    private Integer tripDuration;

    @Column(name = "trip_plan", columnDefinition="text")
    private String tripPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
