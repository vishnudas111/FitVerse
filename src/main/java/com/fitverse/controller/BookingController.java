// BookingController.java
package com.fitverse.controller;

import com.fitverse.entity.Booking;
import com.fitverse.exception.*;
import com.fitverse.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Slf4j
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking bookSlot(@RequestParam String centerId,
                            @RequestParam String userId,
                            @RequestParam String slotId)
            throws BookingException, SlotException, UserException {
        log.info("Booking request - Center: {}, User: {}, Slot: {}", centerId, userId, slotId);
        return bookingService.bookSlot(centerId, userId, slotId);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable String userId) {
        log.info("Fetching bookings for user: {}", userId);
        return bookingService.getUserBookings(userId);
    }

    @DeleteMapping
    public void cancelBooking(@RequestParam String bookingId) throws BookingException {
        log.info("Cancelling booking: {}", bookingId);
        bookingService.cancelBooking(bookingId);
    }
}