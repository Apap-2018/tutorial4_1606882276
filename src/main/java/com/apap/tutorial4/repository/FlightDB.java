package com.apap.tutorial4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;

@Repository
public interface FlightDB extends JpaRepository<FlightModel, Long> {
	FlightModel findById(long Id);
	FlightModel findByFlightNumber(String flightNumber);
}
