package com.hotel.service.controller;

import com.hotel.service.dto.CreateHotelRequest;
import com.hotel.service.dto.HotelResponse;
import com.hotel.service.dto.UpdateHotelRequest;
import com.hotel.service.service.HotelService;
import com.hotel.service.util.RoleValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    //PUBLIC

    @GetMapping("/search")
    public ResponseEntity<List<HotelResponse>> getAllActiveHotels() {
        return ResponseEntity.ok(hotelService.getAllActiveHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

//protected

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(
            @RequestHeader("X-User-Role") String role,
            @Valid @RequestBody CreateHotelRequest request) {

        RoleValidator.checkRole(role, Set.of("ADMIN", "MANAGER"));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelService.createHotel(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> updateHotel(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long id,
            @Valid @RequestBody UpdateHotelRequest request) {

        RoleValidator.checkRole(role, Set.of("ADMIN", "MANAGER"));

        return ResponseEntity.ok(hotelService.updateHotel(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long id) {

        RoleValidator.checkRole(role, Set.of("ADMIN"));

        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
