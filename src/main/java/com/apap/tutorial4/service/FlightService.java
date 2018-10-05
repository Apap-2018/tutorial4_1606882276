package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(FlightModel flight);
	FlightModel getFlightById(long Id);
	FlightModel getFlightByFlightNumber(String flightNumber);
	void updateFlight(long id, FlightModel flightv1);
}
