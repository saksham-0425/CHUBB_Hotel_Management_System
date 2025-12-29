package com.hotel.service.dto;

import com.hotel.service.model.RoomStatus;
import com.hotel.service.model.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoomRequest {
    private RoomType roomType;
    private RoomStatus status;
}
