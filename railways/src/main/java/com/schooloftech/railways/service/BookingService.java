package com.schooloftech.railways.service;

import com.schooloftech.railways.form.BookingForm;
import com.schooloftech.railways.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schooloftech.railways.repository.StationsRepository;
import com.schooloftech.railways.entity.Booking;
import com.schooloftech.railways.form.BookingForm;

import java.awt.print.Book;
import java.time.LocalTime;

@Service
@Slf4j

public class BookingService {
    private final CapacityService capacityService;

    public BookingService(CapacityService capacityService) {
        this.capacityService = capacityService;
    }


    public void bookTicket(BookingForm bookingForm) {
        if (capacityService.checkAvailability(bookingForm)) {
            // Book the ticket
            // Save the ticket information to the database
            capacityService.updateCapacity(bookingForm);
        } else {
            throw new RuntimeException("Not enough seats available");
        }
    }
    @Autowired
    StationsRepository stationsRepository;
    public double calculatefare(String departing_station, String arrival_station, LocalTime departure_time) {
        log.info("departing station : " + departing_station);
        log.info("arrival station: " + arrival_station);
        Integer departStationId = stationsRepository.findIDByStation(departing_station).get(0);
        Integer arriveStationId = stationsRepository.findIDByStation(arrival_station).get(0);
        log.info("departStationid: " + departStationId);
        log.info("arrivalStationID: " + arriveStationId);
        log.info("departure time: " + departure_time);
        double train_fare;
        if (departure_time.isAfter(LocalTime.of(8, 0)) && departure_time.isBefore(LocalTime.of(9, 0))) {
            train_fare = Math.abs(arriveStationId - departStationId) * 10 * 1.25;
        } else {
            train_fare = Math.abs(arriveStationId - departStationId) * 10;
        }
        log.info("calculatefare : " + train_fare);
        return train_fare;
    }
}