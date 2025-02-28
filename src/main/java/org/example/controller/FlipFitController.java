package org.example.controller;

import org.example.service.BookingService;
import org.example.service.CenterService;
import org.example.service.UserService;

import java.util.List;
import java.util.Map;


public class FlipFitController {
    private CenterService centerService = new CenterService();
    private UserService userService = new UserService();
    private BookingService bookingService = new BookingService();

    public void addCenter(String name, String city, List<String> daysClosed, int numberOfSlots) {
        centerService.addCenter(name, city, daysClosed, numberOfSlots);
    }

    public void addWorkoutType(String centerName, String workoutType) {
        centerService.addWorkoutType(centerName, workoutType);
    }

    public void addSlot(String centerName, String workoutType, String startTime, int numberOfSeats) {
        centerService.addSlot(centerName, workoutType, startTime, numberOfSeats);
    }

    public void registerUser(String name, int age, String city) {
        userService.registerUser(name, age, city);
    }

    public void bookSlot(String centerName, String userId, String slotId,String date) {
        bookingService.bookSlot(centerName, userId, slotId, date);

    }

    public void viewUserBooking(String userId, String date) {
        List<Map<String, String>> userBookings = bookingService.viewUserBooking(userId, date);
        if (userBookings.isEmpty()) {
            System.out.println("No bookings found for user: " + userId + " on date: " + date);
        } else {
            System.out.println("Bookings for user: " + userId + " on date: " + date);
            userBookings.forEach(booking -> System.out.println(
                    booking.get("slotId") + "," +
                            booking.get("centerName") + "," +
                            booking.get("workoutType") + "," +
                            booking.get("slotTime")
            ));
        }
    }

    public void cancelSlot(String centerName, String userId, String slotId) {
        bookingService.cancelSlot(centerName, userId, slotId);
    }

    public void getAvailableSlot(String centerName, String date) {
        List<Map<String, String>> availableSlots = centerService.getAvailableSlot(centerName, date);
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots for center: " + centerName + " on date: " + date);
        } else {
            System.out.println("Available slots for center: " + centerName + " on date: " + date);
            availableSlots.forEach(slot -> System.out.println(
                    slot.get("slotId") + "," +
                            slot.get("centerName") + "," +
                            slot.get("workoutType") + "," +
                            slot.get("slotTime") + "," +
                            slot.get("availableSeats")
            ));
        }
    }
}