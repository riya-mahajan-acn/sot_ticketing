package com.schooloftech.railways.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import com.schooloftech.railways.form.BookingForm;

@Service
public class CapacityService {
    private int capacity = 250;
    private final int maxTicket = 5;
    private final List<String> stations = Arrays.asList("Tokyo", "Shinagawa", "Meguro", "Shibuya", "Shinjuku",
            "Ikebukuro", "Komagome", "Nippori", "Ueno", "Akihabara");

    public boolean checkAvailability(BookingForm bookingForm) {
        return capacity >= bookingForm.getnumber_of_people() && bookingForm.getnumber_of_people() <= maxTicket &&
                stations.contains(bookingForm.getdeparting_station()) && stations.contains(bookingForm.getarrival_station());
    }

    public void updateCapacity(BookingForm bookingForm) {
        capacity -= bookingForm.getnumber_of_people();
    }
}