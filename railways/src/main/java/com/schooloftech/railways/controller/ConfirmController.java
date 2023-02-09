package com.schooloftech.railways.controller;

import com.schooloftech.railways.entity.Booking;
import com.schooloftech.railways.repository.BookingRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RestController
public class ConfirmController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/confirmBooking")
    public String confirmTicket(HttpServletRequest request) {
        String departing_st = request.getParameter("departing_station");
        String arrival_st = request.getParameter("arrival_station");
        String departure_date = request.getParameter("departure_date");
        String departureTime = request.getParameter("departure_time");
        Double train_fare = Double.parseDouble(request.getParameter("train_fare"));
        String action = request.getParameter("action");

        Booking booking = new Booking();
        bookingRepository.save(booking);

        return "confirmBooking";
    }
}


