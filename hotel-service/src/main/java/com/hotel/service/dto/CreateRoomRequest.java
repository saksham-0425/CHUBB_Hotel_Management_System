package com.hotel.service.dto;

import com.hotel.service.model.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequest {

    @NotBlank
    private String roomNumber;

    @NotNull
    private RoomType roomType;
}
