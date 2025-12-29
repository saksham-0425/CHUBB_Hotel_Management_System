package com.hotel.service.service;

import com.hotel.service.dto.CreateRoomRequest;
import com.hotel.service.dto.UpdateRoomRequest;
import com.hotel.service.exception.BadRequestException;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.model.Hotel;
import com.hotel.service.model.HotelStatus;
import com.hotel.service.model.Room;
import com.hotel.service.model.RoomStatus;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.repository.RoomRepository;
import com.hotel.service.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Override
    public Room createRoom(Long hotelId, CreateRoomRequest request) {
    	
    	 validateActiveHotel(hotelId);

        if (roomRepository.existsByHotelIdAndRoomNumber(hotelId, request.getRoomNumber())) {
            throw new BadRequestException("Room number already exists for this hotel");
        }

        Room room = Room.builder()
                .hotelId(hotelId)
                .roomNumber(request.getRoomNumber())
                .roomType(request.getRoomType())
                .status(RoomStatus.AVAILABLE)
                .build();

        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRoomsByHotel(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

    @Override
    public Room getRoom(Long hotelId, Long roomId) {
        return roomRepository.findByIdAndHotelId(roomId, hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
    }

    @Override
    public Room updateRoom(Long hotelId, Long roomId, UpdateRoomRequest request) {
    	
    	validateActiveHotel(hotelId);

        Room room = getRoom(hotelId, roomId);

        if (request.getRoomType() != null) {
            room.setRoomType(request.getRoomType());
        }

        if (request.getStatus() != null) {
            if (room.getStatus() == RoomStatus.OCCUPIED) {
                throw new BadRequestException("Cannot update an occupied room");
            }
            room.setStatus(request.getStatus());
        }

        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long hotelId, Long roomId) {
    	
    	validateActiveHotel(hotelId);

        Room room = getRoom(hotelId, roomId);

        if (room.getStatus() == RoomStatus.OCCUPIED) {
            throw new BadRequestException("Cannot delete an occupied room");
        }

        roomRepository.delete(room);
    }
    
    private Hotel validateActiveHotel(Long hotelId) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id: " + hotelId)
                );

        if (hotel.getStatus() != HotelStatus.ACTIVE) {
            throw new BadRequestException(
                    "Operations are not allowed on an inactive hotel"
            );
        }

        return hotel;
    }
}
