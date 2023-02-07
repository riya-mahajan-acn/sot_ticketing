package com.schooloftech.railways.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schooloftech.railways.entity.Schedule;
import com.schooloftech.railways.entity.User;
import com.schooloftech.railways.repository.ScheduleRepository;
import com.schooloftech.railways.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.schooloftech.railways.CSVHelper;
import com.schooloftech.railways.CSVService;
import com.schooloftech.railways.ResponseMessage;

import jakarta.servlet.http.HttpSession;


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
    public String homePage(Model model){
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

    @GetMapping("/{id}")
    public Schedule get(@PathVariable Integer id){
        return schedRepo.getReferenceById(id);
    }
    
}
