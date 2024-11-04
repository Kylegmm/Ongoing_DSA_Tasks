package Assignment1;

import java.util.Scanner;

public class Temperature {
    private int[] temperatures; // Array to store temperatures
    private int days;           // Number of days (temperature entries)

    // Constructor to initialize the Temperature object
    public Temperature(int days) {
        this.days = days;
        this.temperatures = new int[days]; // Create array based on the number of days
    }

    // Method to get temperature inputs from the user
    public void inputTemperatures() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the temperatures for " + days + " days:");

        for (int i = 0; i < days; i++) {
            System.out.print("Day " + (i + 1) + ": ");
            temperatures[i] = scanner.nextInt();
        }
    }

    // Method to calculate the average temperature
    public double calculateAverageTemperature() {
        int sum = 0;
        for (int temp : temperatures) {
            sum += temp; // Sum all temperatures
        }
        return (double) sum / days; // Calculate and return the average
    }

    // Method to count how many days have temperatures above the average
    public int countAboveAverage(double average) {
        int count = 0;
        for (int temp : temperatures) {
            if (temp > average) {
                count++; // Increment count if temperature is above average
            }
        }
        return count;
    }

    // Main method to execute the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Take input for the number of days
        System.out.print("Enter the number of days: ");
        int days = scanner.nextInt();

        // Step 2: Create a Temperature object and get temperatures
        Temperature temperature = new Temperature(days);
        temperature.inputTemperatures();

        // Step 3: Calculate the average temperature
        double averageTemp = temperature.calculateAverageTemperature();
        System.out.println("Average Temperature: " + averageTemp);

        // Step 4: Count how many days were above average and display the result
        int aboveAverageCount = temperature.countAboveAverage(averageTemp);
        System.out.println("Number of days above average temperature: " + aboveAverageCount);
    }
}
