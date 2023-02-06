package com.schooloftech.railways.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schooloftech.railways.repository.ScheduleRepository;

@Controller
@RequestMapping("/sched")
public class SchedulingController {

    @Autowired
    private ScheduleRepository schedRepo;

    @GetMapping("/home")
    public String schedHome(Model model){

        return "sched_home";
    }

}
