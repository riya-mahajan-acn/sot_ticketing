package com.schooloftech.railways.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schooloftech.railways.entity.User;
import com.schooloftech.railways.repository.UserRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/finance")
public class FinanceController{

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/home")
    public String financeHome(Principal p, Model model){
        String em = p.getName();
        User user = userRepo.findByEmail(em);
        model.addAttribute("firstname", user.getFirstName());

        return "/financialHome";
    }
    
}
