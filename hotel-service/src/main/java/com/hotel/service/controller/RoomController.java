package com.hotel.service.controller;

import com.hotel.service.dto.CreateRoomRequest;
import com.hotel.service.dto.RoomResponse;
import com.hotel.service.dto.UpdateRoomRequest;
import com.hotel.service.mapper.RoomMapper;
import com.hotel.service.model.Room;
import com.hotel.service.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public RoomResponse createRoom(
            @PathVariable Long hotelId,
            @Valid @RequestBody CreateRoomRequest request) {

        Room room = roomService.createRoom(hotelId, request);
        return RoomMapper.toResponse(room);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','RECEPTIONIST')")
    public List<RoomResponse> getRooms(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId)
                .stream()
                .map(RoomMapper::toResponse)
                .toList();
    }

    @GetMapping("/{roomId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','RECEPTIONIST')")
    public RoomResponse getRoom(
            @PathVariable Long hotelId,
            @PathVariable Long roomId) {

        Room room = roomService.getRoom(hotelId, roomId);
        return RoomMapper.toResponse(room);
    }

    @PutMapping("/{roomId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public RoomResponse updateRoom(
            @PathVariable Long hotelId,
            @PathVariable Long roomId,
            @Valid @RequestBody UpdateRoomRequest request) {

        Room room = roomService.updateRoom(hotelId, roomId, request);
        return RoomMapper.toResponse(room);
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRoom(
            @PathVariable Long hotelId,
            @PathVariable Long roomId) {

        roomService.deleteRoom(hotelId, roomId);
    }
}
