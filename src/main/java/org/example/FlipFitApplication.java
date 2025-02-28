package org.example;

import org.example.controller.FlipFitController;

import java.util.Arrays;

public class FlipFitApplication {
    public static void main(String[] args) {

        FlipFitController controller = new FlipFitController();

        // Add center and slots
        controller.addCenter("bellandur", "bangalore", Arrays.asList("monday", "sunday"), 5);
        controller.addWorkoutType("bellandur", "weights");
        controller.addWorkoutType("bellandur", "cardio");
        controller.addWorkoutType("bellandur", "yoga");
        controller.addSlot("bellandur", "weights", "6:00", 2);
        controller.addSlot("bellandur", "yoga", "8:00", 1);

        // Register users
        controller.registerUser("Vivek", 16, "bangalore");
        controller.registerUser("Pavan", 20, "bangalore");
        controller.registerUser("Varun", 22, "bangalore");

        //View Available slots
        controller.getAvailableSlot("bellandur","28-05-2021");

        // Book slots
        controller.bookSlot("bellandur", "Vivek", "1","28-05-2021");

        // View user bookings
        controller.viewUserBooking("Vivek", "28-05-2021");

        controller.getAvailableSlot("bellandur","28-05-2021");

        // Cancel a booking
        controller.cancelSlot("bellandur", "Vivek", "1");

        // View user bookings after cancellation
        controller.viewUserBooking("vivek", "28-05-2021");

    }
}