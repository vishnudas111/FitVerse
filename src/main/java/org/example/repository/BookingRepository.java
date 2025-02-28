package org.example.repository;

import org.example.model.Booking;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingRepository {
    private static Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public static void addBooking(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    public static void removeBooking(String bookingId) {
        bookings.remove(bookingId);
    }

    public static List<Booking> getUserBookings(String userId) {
        return bookings.values().stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
