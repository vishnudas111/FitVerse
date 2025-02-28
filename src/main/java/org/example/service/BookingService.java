package org.example.service;


import org.example.model.Booking;
import org.example.model.Center;
import org.example.model.Slot;
import org.example.repository.BookingRepository;
import org.example.repository.CenterRepository;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


public class BookingService {
    private final ReentrantLock lock = new ReentrantLock();

    public void bookSlot(String centerName, String userId, String slotId, String date) {
        Center center = CenterRepository.getCenter(centerName);
        if (center != null) {
            Slot slot = center.getSlots().stream()
                    .filter(s -> s.getSlotId().equals(slotId))
                    .findFirst()
                    .orElse(null);
            if (slot != null) {
                lock.lock();
                try {
                    if (slot.getAvailableSeats() > 0) {
                        Booking booking = new Booking();
                        booking.setBookingId(UUID.randomUUID().toString());
                        booking.setUserId(userId);
                        booking.setSlotId(slotId);
                        booking.setDate(date);
                        booking.setCenterName(centerName);
                        booking.setWorkoutType(slot.getWorkoutType());
                        BookingRepository.addBooking(booking);
                        slot.setAvailableSeats(slot.getAvailableSeats() - 1);
                        System.out.println("Booking successful for user: " + userId + " at center " + centerName + " in slot: " + slotId);
                    } else {
                        slot.getWaitList().add(userId); // Add to waitlist
                        System.out.println("Slot is full. Added to waitlist for user: " + userId);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public void cancelSlot(String centerName, String userId, String slotId) {
        Booking booking = BookingRepository.getUserBookings(userId).stream()
                .filter(b -> b.getSlotId().equals(slotId))
                .findFirst()
                .orElse(null);
        if (booking != null) {
            lock.lock();
            try {
                BookingRepository.removeBooking(booking.getBookingId());
                Center center = CenterRepository.getCenter(centerName);
                if (center != null) {
                    Slot slot = center.getSlots().stream()
                            .filter(s -> s.getSlotId().equals(slotId))
                            .findFirst()
                            .orElse(null);
                    if (slot != null) {
                        slot.setAvailableSeats(slot.getAvailableSeats() + 1);
                        if (!slot.getWaitList().isEmpty()) {
                            String waitlistedUser = slot.getWaitList().remove(0); // Promote waitlisted user
                            System.out.println("Promoted user: " + waitlistedUser + " from waitlist.");
                        }
                        System.out.println("Booking canceled for user: " + userId + " in slot: " + slotId);
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }


    public List<Map<String, String>> viewUserBooking(String userId, String date) {
        return BookingRepository.getUserBookings(userId).stream()
                .filter(booking -> booking != null && booking.getDate() != null && booking.getDate().equals(date))
                .map(booking -> {
                    Map<String, String> bookingDetails = new HashMap<>();
                    if (booking.getCenterName() == null || booking.getSlotId() == null) {
                        bookingDetails.put("error", "Invalid booking data for user: " + userId);
                        return bookingDetails;
                    }
                    Center center = CenterRepository.getCenter(booking.getCenterName());
                    if (center == null) {
                        bookingDetails.put("error", "Center not found for booking: " + booking.getBookingId());
                        return bookingDetails;
                    }
                    Slot slot = center.getSlots().stream()
                            .filter(s -> s != null && s.getSlotId() != null && s.getSlotId().equals(booking.getSlotId()))
                            .findFirst()
                            .orElse(null);
                    if (slot == null) {
                        bookingDetails.put("error", "Slot not found for booking: " + booking.getBookingId());
                        return bookingDetails;
                    }
                    bookingDetails.put("slotId", booking.getSlotId());
                    bookingDetails.put("centerName", center.getName());
                    bookingDetails.put("workoutType", slot.getWorkoutType());
                    bookingDetails.put("slotTime", slot.getStartTime());

                    return bookingDetails;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, String>> getAvailableSlot(String centerName, String date) {
        Center center = CenterRepository.getCenter(centerName);
        if (center != null) {
            return center.getSlots().stream()
                    .filter(slot -> slot.getAvailableSeats() > 0)
                    .map(slot -> {
                        Map<String, String> slotDetails = new HashMap<>();
                        slotDetails.put("slotId", slot.getSlotId());
                        slotDetails.put("centerName", centerName);
                        slotDetails.put("workoutType", slot.getWorkoutType());
                        slotDetails.put("slotTime", slot.getStartTime());
                        slotDetails.put("availableSeats", String.valueOf(slot.getAvailableSeats()));
                        return slotDetails;
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
