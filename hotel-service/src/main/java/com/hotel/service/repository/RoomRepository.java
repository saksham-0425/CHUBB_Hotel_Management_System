package com.hotel.service.repository;

import com.hotel.service.model.Room;
import com.hotel.service.model.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByHotelIdAndRoomNumber(Long hotelId, String roomNumber);

    List<Room> findByHotelId(Long hotelId);

    Optional<Room> findByIdAndHotelId(Long id, Long hotelId);

    List<Room> findByHotelIdAndStatus(Long hotelId, RoomStatus status);
}
