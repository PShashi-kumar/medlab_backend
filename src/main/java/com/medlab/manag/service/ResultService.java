package com.medlab.manag.service;

import com.medlab.manag.entity.Booking;
import com.medlab.manag.entity.BookingStatus;
import com.medlab.manag.entity.Result;
import com.medlab.manag.repository.BookingRepository;
import com.medlab.manag.repository.ResultRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final BookingRepository bookingRepository;

    public ResultService(ResultRepository resultRepository, BookingRepository bookingRepository) {
        this.resultRepository = resultRepository;
        this.bookingRepository = bookingRepository;
    }

    public Result add(Result result) {
        result.setResultDate(LocalDate.now());
        Result saved = resultRepository.save(result);

        // Adding a result marks the booking as COMPLETED
        Booking booking = bookingRepository.findById(result.getBooking().getId()).orElse(null);
        if (booking != null) {
            booking.setStatus(BookingStatus.COMPLETED);
            bookingRepository.save(booking);
        }
        return saved;
    }

    public Result getByBooking(Integer bookingId) {
        return resultRepository.findByBookingId(bookingId);
    }

    public List<Result> getAll() {
        return resultRepository.findAll();
    }
}