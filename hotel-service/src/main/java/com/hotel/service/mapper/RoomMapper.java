package com.hotel.service.mapper;

import com.hotel.service.dto.RoomResponse;
import com.hotel.service.model.Room;

public class RoomMapper {

    public static RoomResponse toResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .hotelId(room.getHotelId())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType())
                .status(room.getStatus())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .build();
    }
}
