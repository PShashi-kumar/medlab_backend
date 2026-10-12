package com.medlab.manag.controller;

import com.medlab.manag.entity.Booking;
import com.medlab.manag.entity.BookingStatus;
import com.medlab.manag.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return bookingService.create(booking);
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<Booking> getByPatient(@PathVariable Integer patientId) {
        return bookingService.getByPatient(patientId);
    }

    // Body example: {"status": "IN_PROGRESS"}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        Booking booking = bookingService.updateStatus(id, BookingStatus.valueOf(body.get("status")));
        if (booking == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Booking not found"));
        }
        return ResponseEntity.ok(booking);
    }
}