package com.schooloftech.railways.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schooloftech.railways.entity.User;
import com.schooloftech.railways.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

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

}
