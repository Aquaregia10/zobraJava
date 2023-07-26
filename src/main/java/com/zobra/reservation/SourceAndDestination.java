
package com.zobra.reservation;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.time.LocalTime;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SourceAndDestination {

	public static void main(String[] args) {
		String[] sourcesCities = { "NEPAL", "INDIA", "PAKISTAN", "USA" };
		int numOfCities = sourcesCities.length;
		int numOfFlightsPerCity = 3;
		int numOfFlights = numOfCities * numOfFlightsPerCity;
		Flight[] flights = new Flight[numOfFlights];

		// Initialize flights from each source city to different destinations
		int flightIndex = 0;
		for (int sourceIndex = 0; sourceIndex < numOfCities; sourceIndex++) {
			for (int destinationIndex = 0; destinationIndex < numOfCities; destinationIndex++) {
				if (destinationIndex != sourceIndex) {
					flights[flightIndex] = new Flight(
							"QA00" + sourcesCities[sourceIndex].charAt(0) + sourcesCities[destinationIndex].charAt(0),
							"Quatar Airlines", LocalTime.of(8, 0), LocalTime.of(10, 30), 300);
					flightIndex++;
				}
			}
		}

		Scanner scanner = new Scanner(System.in);

		System.out.println("Available Cities: ");
		for (int i = 0; i < numOfCities; i++) {
			System.out.println((i + 1) + ". " + sourcesCities[i]);
		}

		System.out.print("Select the source city: ");
		int sourceIndex = scanner.nextInt() - 1;
		String sourceCity = sourcesCities[sourceIndex];

		String[] destinationCities = new String[numOfCities - 1];
		int destIndex = 0;
		for (int i = 0; i < numOfCities; i++) {
			if (i != sourceIndex) {
				destinationCities[destIndex] = sourcesCities[i];
				destIndex++;
			}
		}

		System.out.println("Available Destination Cities:");
		for (int i = 0; i < numOfCities - 1; i++) {
			System.out.println((i + 1) + ". " + destinationCities[i]);
		}

		System.out.print("Select the destination city: ");
		int destinationIndex = scanner.nextInt() - 1;
		String destinationCity = destinationCities[destinationIndex];

		System.out.print("  ");

		System.out.println("Available Flights from " + sourceCity + " to " + destinationCity + ":");
		for (int i = 0; i < numOfFlightsPerCity; i++) {
			int flightNumber = sourceIndex * numOfFlightsPerCity + i;
			System.out.println((i + 1) + ". " + flights[flightNumber].getFlightDetails());
		}

		System.out.print("  ");

		System.out.print("Choose a flight (july/23/23): ");
		int selectedFlight = scanner.nextInt() - 1;
		int chosenFlightIndex = sourceIndex * numOfFlightsPerCity + selectedFlight;
		Flight chosenFlight = flights[chosenFlightIndex];

		// Generate the e-ticket PDF
		String passengerName = "Samyona ";
		String ticketFileName = "src/main/resources/ticket.pdf";

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(ticketFileName));
			document.open();
			document.add(new Paragraph("E-Ticket Details for :" + passengerName));
			document.add(new Paragraph("Passenger Name: " + passengerName));
			document.add(new Paragraph("Source City: " + sourceCity));
			document.add(new Paragraph("Destination City: " + destinationCity));
			document.add(new Paragraph("Flight Details:"));
			document.add(new Paragraph(chosenFlight.getFlightDetails()));
			document.close();
			System.out.println("E-Ticket generated successfully!");
		} catch (Exception e) {
			System.out.println("Error generating E-Ticket: " + e.getMessage());
		}

	}
}
