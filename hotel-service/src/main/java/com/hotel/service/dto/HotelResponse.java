package com.hotel.service.dto;

import com.hotel.service.model.HotelStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelResponse {

    private Long id;
    private String name;
    private String city;
    private String address;
    private Integer totalRooms;
    private HotelStatus status;
}
