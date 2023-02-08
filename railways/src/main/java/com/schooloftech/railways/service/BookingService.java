package com.schooloftech.railways.service;

import com.schooloftech.railways.form.BookingForm;
import org.springframework.stereotype.Service;

@Service
//public class BookingService {
//
//    @Autowired
//    private CapacityService capacityService;
//
//
//    public BookingService(CapacityService capacityService) {
//        this.capacityService = capacityService;
//    }
//
//    public boolean bookTicket(BookingForm bookingForm) {
//        int requestedTickets = bookingForm.getNumberOfTickets();
//        LocalDate travelDate = bookingForm.getTravelDate();
//
//        if (capacityService.checkAvailability(requestedTickets, travelDate)) {
//            capacityService.updateCapacity(requestedTickets, travelDate);
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
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
    public int calculatefare(int departStationId, int arriveStationId) {
        return Math.abs(arriveStationId - departStationId);
    }

}