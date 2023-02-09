package com.schooloftech.railways.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schooloftech.railways.entity.User;
import com.schooloftech.railways.repository.BookingRepository;
import com.schooloftech.railways.repository.ScheduleRepository;
import com.schooloftech.railways.repository.UserRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/finance")
public class FinanceController{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ScheduleRepository schedRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @GetMapping("/home")
    public String financeHome(Principal p, Model model){
        String em = p.getName();
        User user = userRepo.findByEmail(em);
        model.addAttribute("firstname", user.getFirstName());

        return "f_home";
    }

    @GetMapping("/timetable")
    public String getSchedule(Model model) {
            model.addAttribute("schedule", schedRepo.findAll());
        return "f_timetable";
    }

    @GetMapping("/userList")
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "f_passengerList";
    }

    @GetMapping("/allBookings")
    public String allBookings(Model model){
        model.addAttribute("bookings", bookingRepo.findAll());
        return "f_allBookings";
    }
    
}
