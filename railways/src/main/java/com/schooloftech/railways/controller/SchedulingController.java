package com.schooloftech.railways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.schooloftech.railways.CSVHelper;
import com.schooloftech.railways.CSVService;
import com.schooloftech.railways.ResponseMessage;
import com.schooloftech.railways.entity.Schedule;

@Controller
@RequestMapping("/sched")
public class SchedulingController {

  @Autowired
  CSVService fileService;

  @GetMapping("/home")

  public String home(){
    return "sched_update";
  }

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        
        // String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        //         .path("/api/csv/download/")
        //         .path(file.getOriginalFilename())
        //         .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }

  @GetMapping("/timetable")
  public ResponseEntity<List<Schedule>> getallSchedule() {
    try {
      List<Schedule> schedule = fileService.getallSchedule();

      if (schedule.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(schedule, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}