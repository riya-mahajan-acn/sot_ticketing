package com.schooloftech.railways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.schooloftech.railways.CSVHelper;
import com.schooloftech.railways.CSVService;
import com.schooloftech.railways.repository.ScheduleRepository;;

@Controller
@RequestMapping("/sched")
public class SchedulingController {

  @Autowired
  CSVService fileService;

  @Autowired
  ScheduleRepository schedRepo;

  @GetMapping("/home")

  public String home(){
    return "sched_update";
  }

  @PostMapping("/upload")
  public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    ModelAndView modelAndView = new ModelAndView("sched_updateStatus");
    if (CSVHelper.hasCSVFormat(file)) {

      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        modelAndView.addObject("message", message );
        
        // String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        //         .path("/api/csv/download/")
        //         .path(file.getOriginalFilename())
        //         .toUriString();

        return modelAndView;
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        // modelAndView.setViewName(message);
        modelAndView.addObject("message", message );
        return modelAndView;
      }
    }

    message = "Please upload a csv file!";
    modelAndView.addObject("message", message );

    return modelAndView;
  }

  @GetMapping("/timetable")
  public String getSchedule(Model model) {
          model.addAttribute("schedule", schedRepo.findAll());
      return "s_timetable";
  }

}