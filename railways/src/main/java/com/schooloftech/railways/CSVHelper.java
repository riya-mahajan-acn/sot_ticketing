package com.schooloftech.railways;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.schooloftech.railways.entity.Schedule;


public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "train_group", "train_name", "departure_st", "arrival_st", "departure_time" };

    //this
    public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())
            || file.getContentType().equals("application/vnd.ms-excel")) {
        return true;
    }

    return false;
    }

  public static List<Schedule> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Schedule> scheduleList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  Schedule schedule = new Schedule(csvRecord.get("train_group"), 
          csvRecord.get("train_name"), 
          csvRecord.get("departure_st"), 
          csvRecord.get("arrival_st"), 
          LocalTime.parse(csvRecord.get("departure_time")));
    	  scheduleList.add(schedule);
      }

      return scheduleList;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
}