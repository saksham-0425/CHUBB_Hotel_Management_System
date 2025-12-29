package com.hotel.service.service;

import com.hotel.service.dto.CreateHotelRequest;
import com.hotel.service.dto.HotelResponse;
import com.hotel.service.dto.UpdateHotelRequest;
import com.hotel.service.exception.HotelNotFoundException;
import com.hotel.service.model.Hotel;
import com.hotel.service.model.HotelStatus;
import com.hotel.service.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelResponse createHotel(CreateHotelRequest request) {

        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .city(request.getCity())
                .address(request.getAddress())
                .totalRooms(request.getTotalRooms())
                .status(HotelStatus.ACTIVE)
                .build();

        return mapToResponse(hotelRepository.save(hotel));
    }

    @Override
    public HotelResponse updateHotel(Long hotelId, UpdateHotelRequest request) {

        Hotel hotel = hotelRepository
                .findByIdAndStatus(hotelId, HotelStatus.ACTIVE)
                .orElseThrow(() -> new HotelNotFoundException("Active hotel not found"));

        hotel.setName(request.getName());
        hotel.setCity(request.getCity());
        hotel.setAddress(request.getAddress());
        hotel.setTotalRooms(request.getTotalRooms());

        return mapToResponse(hotelRepository.save(hotel));
    }

    @Override
    public void deleteHotel(Long hotelId) {

        Hotel hotel = hotelRepository
                .findByIdAndStatus(hotelId, HotelStatus.ACTIVE)
                .orElseThrow(() -> new HotelNotFoundException("Active hotel not found"));

        hotel.setStatus(HotelStatus.INACTIVE);
        hotelRepository.save(hotel);
    }

    @Override
    public HotelResponse getHotelById(Long hotelId) {

        Hotel hotel = hotelRepository
                .findByIdAndStatus(hotelId, HotelStatus.ACTIVE)
                .orElseThrow(() -> new HotelNotFoundException("Active hotel not found"));

        return mapToResponse(hotel);
    }

    @Override
    public List<HotelResponse> getAllActiveHotels() {
        return hotelRepository.findAllByStatus(HotelStatus.ACTIVE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private HotelResponse mapToResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .city(hotel.getCity())
                .address(hotel.getAddress())
                .totalRooms(hotel.getTotalRooms())
                .status(hotel.getStatus())
                .build();
    }
}
