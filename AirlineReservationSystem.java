
// AirlineReservationSystem.java
import java.util.*;

class Flight {
    String flightNumber, origin, destination;
    int seatsAvailable;

    Flight(String flightNumber, String origin, String destination, int seatsAvailable) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.seatsAvailable = seatsAvailable;
    }

    void displayInfo() {
        System.out.println("Flight: " + flightNumber + " | From: " + origin + " | To: " + destination + " | Seats: " + seatsAvailable);
    }
}

class Booking {
    String passengerName;
    Flight flight;

    Booking(String passengerName, Flight flight) {
        this.passengerName = passengerName;
        this.flight = flight;
    }

    void confirmBooking() {
        if (flight.seatsAvailable > 0) {
            flight.seatsAvailable--;
            System.out.println("Booking confirmed for " + passengerName + " on flight " + flight.flightNumber);
        } else {
            System.out.println("No seats available for flight " + flight.flightNumber);
        }
    }
}

class PaymentGateway {
    static boolean processPayment(String cardNumber, double amount) {
        System.out.println("Processing payment of $" + amount + " using card: " + cardNumber);
        return true; // Simulate success
    }
}

class Ticket {
    static void issueTicket(String passengerName, String flightNumber) {
        System.out.println("Ticket issued for " + passengerName + " on flight " + flightNumber);
    }
}

class ScheduleManager {
    List<Flight> flights = new ArrayList<>();

    void addFlight(Flight flight) {
        flights.add(flight);
        System.out.println("Flight " + flight.flightNumber + " added to schedule.");
    }

    Flight searchFlight(String origin, String destination) {
        for (Flight flight : flights) {
            if (flight.origin.equals(origin) && flight.destination.equals(destination)) {
                return flight;
            }
        }
        return null;
    }
}

public class AirlineReservationSystem {
    public static void main(String[] args) {
        ScheduleManager manager = new ScheduleManager();

        // 1. Add flights to schedule
        Flight f1 = new Flight("A101", "Dhaka", "Chittagong", 5);
        Flight f2 = new Flight("B202", "Dhaka", "Sylhet", 3);
        manager.addFlight(f1);
        manager.addFlight(f2);

        // 2. Search for a flight
        Flight selectedFlight = manager.searchFlight("Dhaka", "Chittagong");
        if (selectedFlight != null) {
            selectedFlight.displayInfo();

            // 3. Make a booking
            Booking booking = new Booking("Rahim", selectedFlight);
            booking.confirmBooking();

            // 4. Payment process
            if (PaymentGateway.processPayment("1234-5678-9876-5432", 5000)) {
                // 5. Issue ticket
                Ticket.issueTicket("Rahim", selectedFlight.flightNumber);
            }
        } else {
            System.out.println("No flights available.");
        }
    }
}
