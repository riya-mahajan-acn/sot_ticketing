package com.schooloftech.railways.repository;

import com.schooloftech.railways.entity.Stations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationsRepository extends JpaRepository<Stations, Integer> {
    @Query("SELECT s.id FROM Stations s WHERE s.stationName = ?1")
    public List<Integer> findIDByStation(String station);

}

