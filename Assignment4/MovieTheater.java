package Assignment4;

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {
    private final String theaterName;
    private final List<Seat> seats = new ArrayList<>(); // List to store all seats

    // Constructor to initialize theater with seats
    public MovieTheater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;

        // Create seating chart by initializing Seat objects
        for (char row = 'A'; row < 'A' + numRows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                String seatNumber = row + String.format("%02d", seatNum);
                seats.add(new Seat(seatNumber));
            }
        }
    }

    // Method to get the name of the theater
    public String getTheaterName() {
        return theaterName;
    }

    // Method to reserve a seat
    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);
        if (requestedSeat == null) {
            System.out.println("Seat " + seatNumber + " does not exist.");
            return false;
        }
        return requestedSeat.reserve();
    }

    // Method to cancel a seat reservation
    public boolean cancelSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);
        if (requestedSeat == null) {
            System.out.println("Seat " + seatNumber + " does not exist.");
            return false;
        }
        return requestedSeat.cancel();
    }

    // Helper method to find a seat by its number
    private Seat findSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null; // Seat not found
    }

    // Method to display the seating chart
    public void displaySeatingChart() {
        System.out.println("Seating Chart for " + theaterName + ":");
        char currentRow = ' ';
        for (Seat seat : seats) {
            if (seat.getSeatNumber().charAt(0) != currentRow) {
                System.out.println(); // New row
                currentRow = seat.getSeatNumber().charAt(0);
                System.out.print("Row " + currentRow + ": ");
            }
            System.out.print(seat.isReserved() ? "[X] " : "[ ] ");
        }
        System.out.println();
    }

    // Inner Seat class
    private class Seat {
        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        // Reserve this seat
        public boolean reserve() {
            if (!reserved) {
                reserved = true;
                System.out.println("Seat " + seatNumber + " reserved.");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " is already reserved.");
                return false;
            }
        }

        // Cancel this seat reservation
        public boolean cancel() {
            if (reserved) {
                reserved = false;
                System.out.println("Reservation for seat " + seatNumber + " canceled.");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " is not reserved.");
                return false;
            }
        }

        // Get seat number
        public String getSeatNumber() {
            return seatNumber;
        }

        // Check if the seat is reserved
        public boolean isReserved() {
            return reserved;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        MovieTheater theater = new MovieTheater("Cinema One", 5, 8); // 5 rows, 8 seats per row
        theater.displaySeatingChart();

        // Testing seat reservation
        theater.reserveSeat("A01");
        theater.reserveSeat("A02");
        theater.displaySeatingChart();

        // Testing canceling reservation
        theater.cancelSeat("A01");
        theater.displaySeatingChart();

        // Testing invalid operations
        theater.reserveSeat("A01"); // Reserve again
        theater.cancelSeat("A01"); // Cancel
        theater.cancelSeat("A01"); // Cancel again
        theater.reserveSeat("Z01"); // Invalid seat
    }
}
