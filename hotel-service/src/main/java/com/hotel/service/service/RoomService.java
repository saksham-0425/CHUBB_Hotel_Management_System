package com.hotel.service.service;

import com.hotel.service.dto.CreateRoomRequest;
import com.hotel.service.dto.UpdateRoomRequest;
import com.hotel.service.model.Room;

import java.util.List;

public interface RoomService {

    Room createRoom(Long hotelId, CreateRoomRequest request);

    List<Room> getRoomsByHotel(Long hotelId);

    Room getRoom(Long hotelId, Long roomId);

    Room updateRoom(Long hotelId, Long roomId, UpdateRoomRequest request);

    void deleteRoom(Long hotelId, Long roomId);
}
