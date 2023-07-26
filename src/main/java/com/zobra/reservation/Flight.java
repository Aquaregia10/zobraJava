package com.zobra.reservation;

import java.time.LocalTime;

public class Flight {
	String flightNumber;
	String companyName;
	LocalTime departureTime;
	LocalTime arrivalTime;
	double fare;

	public Flight(String flightNumber, String companyName, LocalTime departureTime, LocalTime arrivalTime,
			double fare) {
		this.flightNumber = flightNumber;
		this.companyName = companyName;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fare = fare;
	}

	public String getFlightDetails() {
		return "Flight Number: " + flightNumber + "\nCompany Name: " + companyName + "\nDeparture Time: "
				+ departureTime + "\nArrival Time: " + arrivalTime + "\nFare: $" + fare;
	}
}
