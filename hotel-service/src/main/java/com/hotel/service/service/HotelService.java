package com.hotel.service.service;

import com.hotel.service.dto.CreateHotelRequest;
import com.hotel.service.dto.HotelResponse;
import com.hotel.service.dto.UpdateHotelRequest;

import java.util.List;

public interface HotelService {

    HotelResponse createHotel(CreateHotelRequest request);

    HotelResponse updateHotel(Long hotelId, UpdateHotelRequest request);

    void deleteHotel(Long hotelId);

    HotelResponse getHotelById(Long hotelId);

    List<HotelResponse> getAllActiveHotels();
}
