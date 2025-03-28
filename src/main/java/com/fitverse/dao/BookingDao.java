package com.fitverse.dao;

// BookingDao.java

import com.fitverse.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingDao {
    void addBooking(Booking booking);
    Optional<Booking> getBooking(String bookingId);
    List<Booking> getUserBookings(String userId);
    void cancelBooking(String bookingId);
}