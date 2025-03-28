package com.fitverse.dao;

// BookingDaoImpl.java

import com.fitverse.entity.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class BookingDaoImpl implements BookingDao {
    private final Map<String, Booking> bookings = new HashMap<>();

    @Override
    public void addBooking(Booking booking) {
        log.debug("Adding booking: {}", booking.getBookingId());
        bookings.put(booking.getBookingId(), booking);
    }

    @Override
    public Optional<Booking> getBooking(String bookingId) {
        log.debug("Fetching booking: {}", bookingId);
        return Optional.ofNullable(bookings.get(bookingId));
    }

    @Override
    public List<Booking> getUserBookings(String userId) {
        log.debug("Fetching bookings for user: {}", userId);
        return bookings.values().stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .toList();
    }

    @Override
    public void cancelBooking(String bookingId) {
        log.info("Cancelling booking: {}", bookingId);
        bookings.remove(bookingId);
    }
}