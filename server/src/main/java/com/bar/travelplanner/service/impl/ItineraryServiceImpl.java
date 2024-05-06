package com.bar.travelplanner.service.impl;

import com.bar.travelplanner.dto.ItineraryRequestDTO;
import com.bar.travelplanner.dto.ItineraryResponseDTO;
import com.bar.travelplanner.dto.mapper.ItineraryResponseDTOMapper;
import com.bar.travelplanner.entity.Itinerary;
import com.bar.travelplanner.entity.User;
import com.bar.travelplanner.exception.ResourceNotFoundException;
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

    public final ModelMapper modelMapper;

    private final ChatGPTRequestHandlerService chatGPTRequestHandlerService;
    private final SecurityService securityService;

    private final ItineraryResponseDTOMapper itineraryResponseDTOMapper;

    @Override
    public ItineraryResponseDTO addItinerary(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException {

        User currentUser = securityService.getCurrentUser();
        itineraryRequestDTO.setUser(currentUser);

        Itinerary itinerary = modelMapper.map(itineraryRequestDTO, Itinerary.class);

        String response = createChatGPTAnswer(itineraryRequestDTO);
        log.info("Chat response" + response);
        itinerary.setTripPlan(response);

        itineraryRepository.save(itinerary);

        return itineraryResponseDTOMapper.toItineraryResponseDTO(itinerary);
    }

    public String createChatGPTAnswer(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException {
        return chatGPTRequestHandlerService.createAnswer(itineraryRequestDTO);
    }

    @Override
    public ItineraryResponseDTO getItineraryById(Long id) {

        return itineraryRepository.findById(id)
                .map(itineraryResponseDTOMapper::toItineraryResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + id));

    }

    @Override
    public List<ItineraryResponseDTO> getAllItineraries() {
        User currentUser = securityService.getCurrentUser();

        List<Itinerary> itineraries = itineraryRepository.findAllItinerariesByUserId(currentUser.getId());
        List<ItineraryResponseDTO> itineraryResponsDTOS = itineraries.stream()
                .map(itineraryResponseDTOMapper::toItineraryResponseDTO)
                .collect(Collectors.toList());

        return itineraryResponsDTOS;
    }

    @Override
    public ItineraryResponseDTO updateItinerary(ItineraryRequestDTO itineraryRequestDTO, Long id) throws IOException, InterruptedException {

        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + id));

        itinerary.setDestination(itineraryRequestDTO.getDestination());
        itinerary.setActivityTypes(itineraryRequestDTO.getActivityTypes());
        itinerary.setTripDuration(itineraryRequestDTO.getTripDuration());

        String response = createChatGPTAnswer(itineraryRequestDTO);
        itinerary.setTripPlan(response);

        itineraryRepository.save(itinerary);

        return itineraryResponseDTOMapper.toItineraryResponseDTO(itinerary);
    }

    @Override
    public void deleteItinerary(Long id) {
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + id));

        itineraryRepository.deleteById(id);
    }


}
