package com.bar.travelplanner.controller;

import com.bar.travelplanner.dto.ItineraryRequestDTO;
import com.bar.travelplanner.dto.ItineraryResponseDTO;
import com.bar.travelplanner.service.ItineraryService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/itineraries")
@AllArgsConstructor
@Log4j2
public class ItineraryController {

    private ItineraryService itineraryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ItineraryResponseDTO> addItinerary(@RequestBody ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException {
        ItineraryResponseDTO itineraryResponseDTO = itineraryService.addItinerary(itineraryRequestDTO);
        return new ResponseEntity<>(itineraryResponseDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<ItineraryResponseDTO> getItinerary(@PathVariable("id") Long itineraryId) {
        ItineraryResponseDTO itineraryResponseDTO = itineraryService.getItineraryById(itineraryId);
        return new ResponseEntity<>(itineraryResponseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<ItineraryResponseDTO>> getAllItineraries() {
        List<ItineraryResponseDTO> itineraries = itineraryService.getAllItineraries();
        //return new ResponseEntity<>(itineraries, HttpStatus.OK);
        return ResponseEntity.ok(itineraries);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<ItineraryResponseDTO> updateItinerary(@RequestBody ItineraryRequestDTO itineraryRequestDTO, @PathVariable("id") Long itineraryId) throws IOException, InterruptedException {
        ItineraryResponseDTO itineraryResponseDTO = itineraryService.updateItinerary(itineraryRequestDTO, itineraryId);
        return ResponseEntity.ok(itineraryResponseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItinerary(@PathVariable("id") Long itineraryId) {
        itineraryService.deleteItinerary(itineraryId);
        return ResponseEntity.ok("Itinerary deleted successfully!");
    }
}