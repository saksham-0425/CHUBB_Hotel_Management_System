package com.hotel.service.repository;

import com.hotel.service.model.Hotel;
import com.hotel.service.model.HotelStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByIdAndStatus(Long id, HotelStatus status);

    List<Hotel> findAllByStatus(HotelStatus status);
}
