package com.schooloftech.railways.controller;

import com.schooloftech.railways.form.BookingForm;
import com.schooloftech.railways.repository.StationsRepository;
import com.schooloftech.railways.service.BookingService;
import com.schooloftech.railways.service.CapacityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private StationsRepository stationsRepository;
    @Autowired
    private CapacityService capacityService;

    @GetMapping("/confirmation")
    public String bookTicketForm(Model model) {
        model.addAttribute("bookingForm", new BookingForm());
        return "confirmation";
    }

    @PostMapping("/confirmation")
    public String bookTicket(@ModelAttribute("bookingForm") BookingForm bookingForm, Model model) {
    log.info("inside bookTicket bookingForm; {}", bookingForm);
    log.info("number of people=" + bookingForm.getnumber_of_people() + "\n source station" + bookingForm.getdeparting_station() + "\n destination" + bookingForm.getarrival_station() +"\n date"+bookingForm.getdeparture_date() );
        if (capacityService.checkAvailability(bookingForm)) {
            bookingService.bookTicket(bookingForm);
            capacityService.updateCapacity(bookingForm);
            model.addAttribute("departing_station", bookingForm.getdeparting_station());
            model.addAttribute("arrival_station", bookingForm.getarrival_station());
            model.addAttribute("departure_date", bookingForm.getdeparture_date());
            model.addAttribute("departure_time", bookingForm.getDeparture_time());
            model.addAttribute("number_of_people", bookingForm.getnumber_of_people());
            double trainFare= bookingService.calculatefare(bookingForm.getdeparting_station(), bookingForm.getarrival_station(), bookingForm.getDeparture_time());
            model.addAttribute("train_fare", trainFare);
            return "confirmation";
        } else {
            model.addAttribute("errorMessage", "Sorry, the tickets for the selected train are not available.");
            return "redirect:/home";
        }

    }



}
