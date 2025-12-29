package com.hotel.service.controller;

import com.hotel.service.dto.CreateRoomRequest;
import com.hotel.service.dto.UpdateRoomRequest;
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
    public Room createRoom(
            @PathVariable Long hotelId,
            @Valid @RequestBody CreateRoomRequest request) {
        return roomService.createRoom(hotelId, request);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','RECEPTIONIST')")
    public List<Room> getRooms(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId);
    }

    @GetMapping("/{roomId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','RECEPTIONIST')")
    public Room getRoom(
            @PathVariable Long hotelId,
            @PathVariable Long roomId) {
        return roomService.getRoom(hotelId, roomId);
    }

    @PutMapping("/{roomId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Room updateRoom(
            @PathVariable Long hotelId,
            @PathVariable Long roomId,
            @RequestBody UpdateRoomRequest request) {
        return roomService.updateRoom(hotelId, roomId, request);
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
