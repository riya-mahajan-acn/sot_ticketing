package com.schooloftech.railways.repository;

import com.schooloftech.railways.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingFormRepository extends JpaRepository<Booking, Long> {
}