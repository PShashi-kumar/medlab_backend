package com.medlab.manag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medlab.manag.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    Result findByBookingId(Integer bookingId);
}