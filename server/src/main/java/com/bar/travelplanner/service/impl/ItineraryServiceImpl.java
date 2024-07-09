package com.bar.travelplanner.service.impl;

import com.bar.travelplanner.dto.ItineraryRequestDTO;
import com.bar.travelplanner.dto.ItineraryResponseDTO;
import com.bar.travelplanner.dto.mapper.ItineraryResponseDTOMapper;
import com.bar.travelplanner.entity.Itinerary;
import com.bar.travelplanner.entity.User;
import com.bar.travelplanner.exception.ResourceNotFoundException;
import com.bar.travelplanner.factory.ItineraryFactory;
import com.bar.travelplanner.repository.ItineraryRepository;
import com.bar.travelplanner.service.ChatGPTRequestHandlerService;
import com.bar.travelplanner.service.ItineraryService;
import com.bar.travelplanner.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final ChatGPTRequestHandlerService chatGPTRequestHandlerService;
    private final SecurityService securityService;
    private final ItineraryFactory itineraryFactory;


    @Override
    public ItineraryResponseDTO addItinerary(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException {
        User currentUser = securityService.getCurrentUser();
        Itinerary itinerary = itineraryFactory.createItinerary(itineraryRequestDTO, currentUser);
        String tripPlan = chatGPTRequestHandlerService.generateTripPlan(itineraryRequestDTO);
        itinerary.setTripPlan(tripPlan);
        Itinerary savedItinerary = itineraryRepository.save(itinerary);
        log.info("Itinerary created for user: {}", currentUser.getUsername());

        return itineraryFactory.createItineraryResponseDTO(savedItinerary);
    }

    @Override
    public ItineraryResponseDTO getItineraryById(Long id) {
        return itineraryRepository.findById(id)
                .map(itineraryFactory::createItineraryResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + id));

    }

    @Override
    public List<ItineraryResponseDTO> getAllItineraries() {
        User currentUser = securityService.getCurrentUser();
        List<Itinerary> itineraries = itineraryRepository.findAllItinerariesByUserId(currentUser.getId());

        return itineraries.stream()
                .map(itineraryFactory::createItineraryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ItineraryResponseDTO updateItinerary(ItineraryRequestDTO itineraryRequestDTO, Long id) throws IOException, InterruptedException {
        log.info("Updating itinerary with id: {}", id);
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + id));
        itineraryFactory.updateItinerary(itinerary, itineraryRequestDTO);
        String tripPlan = chatGPTRequestHandlerService.generateTripPlan(itineraryRequestDTO);
        itinerary.setTripPlan(tripPlan);
        Itinerary savedItinerary = itineraryRepository.save(itinerary);
        log.info("Itinerary updated successfully");

        return itineraryFactory.createItineraryResponseDTO(savedItinerary);
    }

    @Override
    public void deleteItinerary(Long id) {
        log.info("Deleting itinerary with id: {}", id);
        if (!itineraryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Itinerary not found with id: " + id);
        }
        itineraryRepository.deleteById(id);
        log.info("Itinerary deleted successfully");
    }
}