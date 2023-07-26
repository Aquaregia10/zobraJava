package com.zobra.reservation;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Scanner;
public class CityArrays {
    public static void main(String[] args) {
        // Create arrays for source cities and destination cities
        String[] sourceCities = {
            "New York",
            "Doha",
            "Nepal",
            "Pokhara",
            "India"
        };
        String[] destinationCities = {
            "New York",
            "Doha",
            "Nepal",
            "Pokhara",
            "India"
        };
        // Print the source cities
        System.out.println("Source Cities:");
        for (String city: sourceCities) {
            System.out.println(city);
        }
        // Ask user to select a source city for travel
        System.out.print("\nPlease select a source city for travel: ");
        Scanner scanner = new Scanner(System.in);
        String selectedCity = scanner.nextLine();
        // Display the result
        boolean cityExists = false;
        for (String city: sourceCities) {
            if (selectedCity.equalsIgnoreCase(city)) {
                cityExists = true;
                break;
            }
        }
        if (cityExists) {
            System.out.println("You have selected " + selectedCity + " as your source city for travel.");
            // Display the destination cities excluding the selected source city
            System.out.println("\nDestination Cities:");
            for (String city: destinationCities) {
                if (!selectedCity.equalsIgnoreCase(city)) {
                    System.out.println(city);
                }
            }
            // Ask user to select a destination city
            System.out.print("\nPlease select a destination city: ");
            String selectedDestinationCity = scanner.nextLine();
            // Validate the selected destination city
            boolean destinationCityExists = false;
            for (String city: destinationCities) {
                if (selectedDestinationCity.equalsIgnoreCase(city)) {
                    destinationCityExists = true;
                    break;
                }
            }
            if (destinationCityExists) {
                System.out.println("You have selected " + selectedDestinationCity + " as your destination city.");
                // Display available flights for the route (from selectedCity to
                // selectedDestinationCity)
                String[] flightNumbers = {
                    "F111",
                    "F222",
                    "F333"
                };
                String[] companyNames = {
                    "Quatar AirWaya",
                    "American Airlines",
                    "Air India"
                };
                String[] departureTimes = {
                    "08:00 AM",
                    "12:00 PM",
                    "04:00 PM"
                };
                String[] arrivalTimes = {
                    "09:00 pm",
                    "11 PM",
                    "07:00 AM"
                };
                int[] totalTimes = {
                    13,
                    11,
                    15
                };
                double[] fares = {
                    2000.0,
                    1800.0,
                    1745.0
                };
                System.out.println("\nAvailable Flights for " + selectedCity + " to " + selectedDestinationCity + ":");
                for (int i = 0; i < flightNumbers.length; i++) {
                    System.out.println((i + 1) + ". Flight Number: " + flightNumbers[i]);
                    System.out.println("   Company Name: " + companyNames[i]);
                    System.out.println("   Departure Time: " + departureTimes[i]);
                    System.out.println("   Arrival Time: " + arrivalTimes[i]);
                    System.out.println("   Total Time Taken: " + totalTimes[i] + " hours");
                    System.out.println("   Fare: $" + fares[i]);
                    System.out.println();
                }
                // Ask the customer to choose a flight
                System.out.print("Choose a flight (enter 1, 2, or 3): ");
                int chosenFlightIndex = scanner.nextInt();
                // Display the confirmation message with chosen flight details
                if (chosenFlightIndex >= 1 && chosenFlightIndex <= flightNumbers.length) {
                    System.out.println("\nYou have chosen the following flight:");
                    System.out.println("Flight Number: " + flightNumbers[chosenFlightIndex - 1]);
                    System.out.println("Company Name: " + companyNames[chosenFlightIndex - 1]);
                    System.out.println("Departure Time: " + departureTimes[chosenFlightIndex - 1]);
                    System.out.println("Arrival Time: " + arrivalTimes[chosenFlightIndex - 1]);
                    System.out.println("Total Time Taken: " + totalTimes[chosenFlightIndex - 1] + " hours");
                    System.out.println("Fare: $" + fares[chosenFlightIndex - 1]);
                    // Generate e-ticket and save it as a PDF file
                    generateETicketPDF("Samyona Thapa", selectedCity, selectedDestinationCity,
                        flightNumbers[chosenFlightIndex - 1], companyNames[chosenFlightIndex - 1],
                        departureTimes[chosenFlightIndex - 1], arrivalTimes[chosenFlightIndex - 1],
                        totalTimes[chosenFlightIndex - 1], fares[chosenFlightIndex - 1]);
                } else {
                    System.out.println("Invalid choice! Please select a valid flight (1, 2, or 3).");
                }
            } else {
                System.out.println("Invalid destination city! The selected city is not available.");
            }
        } else {
            System.out.println("Invalid selection! The selected city is not available.");
        }
    }
    // Method to generate e-ticket in PDF format and save it as a file
    private static void generateETicketPDF(String customerName, String sourceCity, String destinationCity,
        String flightNumber, String companyName, String departureTime, String arrivalTime, int totalTime,
        double fare) {
        try {
            // Create a new PDF document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Ticket.pdf"));
            // Open the document
            document.open();
            // Add e-ticket details to the document
            document.add(new Paragraph("*** E-Ticket ***"));
            document.add(new Paragraph("Customer Name: " + customerName));
            document.add(new Paragraph("Source City: " + sourceCity));
            document.add(new Paragraph("Destination City: " + destinationCity));
            document.add(new Paragraph("Flight Number: " + flightNumber));
            document.add(new Paragraph("Company Name: " + companyName));
            document.add(new Paragraph("Departure Time: " + departureTime));
            document.add(new Paragraph("Arrival Time: " + arrivalTime));
            document.add(new Paragraph("Total Time Taken: " + totalTime + " hours"));
            document.add(new Paragraph("Fare: $" + fare));
            document.add(new Paragraph("Thank you for choosing our services!"));
            // Close the document
            document.close();
            System.out.println("E-ticket generated successfully and saved as 'resources/ticket.pdf'.");
        } catch (Exception e) {
            System.out.println("Error occurred while generating the e-ticket PDF: " + e.getMessage());
        }
    }
}
// Please paste your code here and press Beautify button