package com.hotel.service.dto;

import com.hotel.service.model.RoomStatus;
import com.hotel.service.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private Long hotelId;

    private String roomNumber;
    private RoomType roomType;
    private RoomStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
