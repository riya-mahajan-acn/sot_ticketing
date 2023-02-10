package com.schooloftech.railways.controller;

import ch.qos.logback.core.model.Model;
import com.schooloftech.railways.entity.Booking;
import com.schooloftech.railways.form.BookingForm;
import com.schooloftech.railways.repository.BookingRepository;
import com.schooloftech.railways.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    private BookingForm bookingForm;

    @PostMapping("/confirmBooking")

    public String confirmBooking(Model model){
        Booking booking = convertToBooking(bookingForm);
        bookingRepository.save(booking);
        return "confirmBooking";
    }

    @PostMapping("/cancel")
    public String cancelBooking(Model model){
        bookingForm.clear();
        return "home";
    }
    private Booking convertToBooking(BookingForm bookingForm){
        Booking booking=new Booking();
        booking.setDeparture_st(bookingForm.getdeparting_station());
        booking.setDeparture_date(java.sql.Date.valueOf(bookingForm.getdeparture_date()));
        booking.setDeparture_time(java.sql.Time.valueOf(bookingForm.getDeparture_time()));
        booking.setNumber_of_tickets(bookingForm.getnumber_of_people());
        booking.setTrain_fare((int) bookingForm.gettrain_fare());
        return booking;
    }
}



