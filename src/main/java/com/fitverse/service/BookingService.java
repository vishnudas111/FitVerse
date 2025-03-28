package com.fitverse.service;


// BookingService.java

import com.fitverse.entity.*;
import com.fitverse.exception.*;
import com.fitverse.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CenterRepository centerRepository;
    private final UserRepository userRepository;
    private final SlotRepository slotRepository;
    private final NotificationService notificationService;

    public BookingService(BookingRepository bookingRepository,
                          CenterRepository centerRepository,
                          UserRepository userRepository,
                          SlotRepository slotRepository,
                          NotificationService notificationService) {
        this.bookingRepository = bookingRepository;
        this.centerRepository = centerRepository;
        this.userRepository = userRepository;
        this.slotRepository = slotRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public Booking bookSlot(String centerId, String userId, String slotId)
            throws BookingException, SlotException, UserException {
        log.info("Attempting to book slot {} for user {} at center {}", slotId, userId, centerId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found: " + userId));

        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new CenterException("Center not found: " + centerId));

        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new SlotException("Slot not found: " + slotId));

        try {
            slot.getLock().lock();

            if (slot.getAvailableSeats() <= 0) {
                log.warn("No available seats in slot {}", slotId);
                throw new BookingException("No seats available in this slot");
            }

            if (slot.getBookedUsers().contains(userId)) {
                log.warn("User {} already booked in slot {}", userId, slotId);
                throw new BookingException("User already booked in this slot");
            }

            slot.setAvailableSeats(slot.getAvailableSeats() - 1);
            slot.getBookedUsers().add(userId);
            slotRepository.save(slot);

            Booking booking = new Booking();
            booking.setBookingId("booking-" + System.currentTimeMillis());
            booking.setUser(user);
            booking.setCenter(center);
            booking.setSlot(slot);
            booking.setBookingDate(new Date());
            booking.setStatus(BookingStatus.CONFIRMED);

            Booking savedBooking = bookingRepository.save(booking);
            log.info("Successfully booked slot {} for user {}", slotId, userId);

            promoteFromWaitlist(slot);

            return savedBooking;
        } finally {
            slot.getLock().unlock();
        }
    }

    private void promoteFromWaitlist(Slot slot) {
        if (slot.getAvailableSeats() > 0 && !slot.getWaitlist().getUserIds().isEmpty()) {
            String userId = slot.getWaitlist().removeNextUser();
            if (userId != null) {
                try {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserException("Waitlisted user not found: " + userId));

                    slot.setAvailableSeats(slot.getAvailableSeats() - 1);
                    slot.getBookedUsers().add(userId);
                    slotRepository.save(slot);

                    Booking booking = new Booking();
                    booking.setBookingId("booking-" + System.currentTimeMillis());
                    booking.setUser(user);
                    booking.setCenter(slot.getCenter());
                    booking.setSlot(slot);
                    booking.setBookingDate(new Date());
                    booking.setStatus(BookingStatus.CONFIRMED);

                    bookingRepository.save(booking);
                    log.info("Promoted user {} from waitlist for slot {}", userId, slot.getSlotId());

                    notificationService.notifyWaitlistPromotion(
                            user,
                            slot.getCenter().getName(),
                            slot.getTime(),
                            slot.getWorkoutType()
                    );
                } catch (Exception e) {
                    log.error("Error promoting user from waitlist: {}", e.getMessage());
                }
            }
        }
    }

    public List<Booking> getUserBookings(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Transactional
    public void cancelBooking(String bookingId) throws BookingException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingException("Booking not found"));

        Slot slot = booking.getSlot();
        try {
            slot.getLock().lock();

            slot.setAvailableSeats(slot.getAvailableSeats() + 1);
            slot.getBookedUsers().remove(booking.getUser().getUserId());
            slotRepository.save(slot);

            bookingRepository.delete(booking);
            log.info("Cancelled booking {} for user {}", bookingId, booking.getUser().getUserId());

            promoteFromWaitlist(slot);
        } finally {
            slot.getLock().unlock();
        }
    }
}
