package com.medlab.manag.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.medlab.manag.entity.Booking;
import com.medlab.manag.entity.BookingStatus;
import com.medlab.manag.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking create(Booking booking) {
        booking.setStatus(BookingStatus.PENDING);
        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDate.now());
        }
        return bookingRepository.save(booking);
    }

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> getByPatient(Integer patientId) {
        return bookingRepository.findByPatientId(patientId);
    }

    public Booking updateStatus(Integer id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) return null;
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }
}