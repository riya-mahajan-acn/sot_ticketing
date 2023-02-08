package com.schooloftech.railways.controller;
import com.schooloftech.railways.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.schooloftech.railways.entity.Schedule;
import com.schooloftech.railways.entity.User;
import com.schooloftech.railways.repository.ScheduleRepository;
import com.schooloftech.railways.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.multipart.MultipartFile;

import com.schooloftech.railways.CSVHelper;
import com.schooloftech.railways.CSVService;
import com.schooloftech.railways.ResponseMessage;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ScheduleRepository schedRepo;

    @Autowired
    CSVService fileService;

    //returns a thymeleaf template

    @GetMapping("/home")
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String homePage(Model model){
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
        return "home";
    }

    @PostMapping
    public String submitSearch(Model model){
        return "hello";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/createUser")
    public String registration(@ModelAttribute User user, HttpSession session){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
        user.setRole("USER");
        userRepo.save(user);
        session.setAttribute("message", "User Registered Successfully");

        return "redirect:/home";
    }

    @GetMapping("/timetable")
    public String getSchedule(Model model) {
            model.addAttribute("schedule", schedRepo.findAll());
        return "timetable";
    }

    @GetMapping("/contactus")
    public String contactus() {
        return "contactus";
    }

    @GetMapping("/{id}")
    public Schedule get(@PathVariable Integer id){
        return schedRepo.getReferenceById(id);
    }

    
}
