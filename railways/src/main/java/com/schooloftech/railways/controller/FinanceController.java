package com.schooloftech.railways.controller;

import java.security.Principal;
import java.util.Map;
import java.util.TreeMap;

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


    //private static final Random RANDOM = new Random(System.currentTimeMillis());
    @GetMapping("/perStationChart")
    public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Tokyo", 147);
        graphData.put("Shinagawa", 1256);
        graphData.put("Meguro", 3856);
        graphData.put("Shibuya", 6500);
        graphData.put("Shinjuku", 5000);
        graphData.put("Ikebukuro", 200);
        graphData.put("Komagome", 700);
        graphData.put("Nippori", 1500);
        graphData.put("Ueno", 198);
        graphData.put("Akihabara", 1980);
        model.addAttribute("chartData", graphData);
        return "f_perStationChart";
    }
}
