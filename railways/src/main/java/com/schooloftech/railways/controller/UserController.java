package com.schooloftech.railways.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.schooloftech.railways.entity.User;
import com.schooloftech.railways.form.BookingForm;
import com.schooloftech.railways.repository.ScheduleRepository;
import com.schooloftech.railways.repository.StationsRepository;
import com.schooloftech.railways.repository.UserRepository;
import com.schooloftech.railways.service.BookingService;
import com.schooloftech.railways.service.CapacityService;
import lombok.extern.slf4j.Slf4j;
@Controller@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired    private UserRepository userRepo;
    @Autowired    private BookingService bookingService;
    @Autowired    private StationsRepository stationsRepository;
    @Autowired    private CapacityService capacityService;
    @Autowired    private ScheduleRepository schedRepo;
    @GetMapping("/home")
    public String userHome(Principal p, Model model){
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("firstName", user.getFirstName());
        return "userHome";
    }
    @GetMapping("/personalDetails")
    public String personalDetailsPage(Principal p,Model model){
        String em = p.getName();
        User user = userRepo.findByEmail(em);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        return "personalDetails";
    }
    @GetMapping("/timetable")
    public String getSchedule(Model model) {
        model.addAttribute("schedule", schedRepo.findAll());
        return "timetable";
    }
    @GetMapping("/bookTickets")
    public String homePage(Principal p, Model model){
        //Booking booking=new Booking();
        //model.addAttribute("Booking",booking);
        List<String> stations = new ArrayList<String>();
        stations.add("Tokyo");
        stations.add("Shinagawa");
        stations.add("Meguro");
        stations.add("Shibuya");
        stations.add("Shinjuku");
        stations.add("Ikebukuro");
        stations.add("Komagome");
        stations.add("Nippori");
        stations.add("Ueno");
        stations.add("Akihabara");
        model.addAttribute("stations",stations);
        return "u_booking";
    }
    @GetMapping("/confirmation")
    public String bookTicketForm(Model model) {
        model.addAttribute("bookingForm", new BookingForm());
        return "u_confirmation";
    }
    @PostMapping("/confirmation")
    public String bookTicket(@ModelAttribute("bookingForm") BookingForm bookingForm, Model model, Principal p) {
        String em = p.getName();
        User user = userRepo.findByEmail(em);
        Integer custId = user.getId();
        bookingForm.setCustId(custId);
        System.out.println(stationsRepository.findIDByStation(bookingForm.getdeparting_station()));
        System.out.println(stationsRepository.findIDByStation(bookingForm.getarrival_station()));
        System.out.println(bookingForm.getdeparting_station());
        System.out.println(bookingForm.getarrival_station());
        log.info("inside bookTicket bookingForm; {}", bookingForm);
        log.info("number of people=" + bookingForm.getnumber_of_people() + "\n source station" + bookingForm.getdeparting_station() + "\n destination" + bookingForm.getarrival_station() +"\n date"+bookingForm.getdeparture_date() +"\n custId"+bookingForm.getCustId() );
        if (capacityService.checkAvailability(bookingForm)) {
            bookingService.bookTicket(bookingForm);
            capacityService.updateCapacity(bookingForm);
            model.addAttribute("custId", bookingForm.getCustId());
            model.addAttribute("departing_station", bookingForm.getdeparting_station());
            model.addAttribute("arrival_station", bookingForm.getarrival_station());
            model.addAttribute("departure_date", bookingForm.getdeparture_date());
            model.addAttribute("departure_time", bookingForm.getDeparture_time());
            model.addAttribute("number_of_people", bookingForm.getnumber_of_people());
            double trainFare= bookingService.calculatefare(bookingForm.getdeparting_station(), bookingForm.getarrival_station(), bookingForm.getDeparture_time());
            model.addAttribute("train_fare", trainFare);
            return "u_confirmation";
        } else {
            model.addAttribute("errorMessage", "Sorry, the tickets for the selected train are not available.");
            return "redirect:/home";
        }
    }
}