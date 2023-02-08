package com.schooloftech.railways.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schooloftech.railways.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}