package com.schooloftech.railways;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.schooloftech.railways.entity.Schedule;
import com.schooloftech.railways.repository.ScheduleRepository;

@Service
public class CSVService {

    @Autowired
    ScheduleRepository schedRepo;


    //save the csv into our database
    public void save(MultipartFile file) {
      try {
        List<Schedule> schedule = CSVHelper.csvToschedules(file.getInputStream());
        schedRepo.saveAll(schedule);
      } catch (IOException e) {
        throw new RuntimeException("fail to store csv data: " + e.getMessage());
      }
    }

    public List<Schedule> getallSchedule(){
        return schedRepo.findAll();
      }
    
}
