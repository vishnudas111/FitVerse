package com.fitverse.service;


import com.fitverse.entity.*;
import com.fitverse.exception.*;
import com.fitverse.repository.BookingRepository;
import com.fitverse.repository.CenterRepository;
import com.fitverse.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private CenterRepository centerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BookingService bookingService;

    private Center testCenter;
    private User testUser;
    private Slot testSlot;

    @BeforeEach
    void setUp() {
        testCenter = new Center("1", "Test Center", "Bangalore", List.of("Sunday"));
        testUser = new User("user1", "Test User", 25, "Bangalore");

        testSlot = new Slot("slot1", "1", "Weights", "06:00", 10);
        testSlot.setAvailableSeats(5);
        testCenter.addSlot(testSlot);
    }

    @Test
    void bookSlot_Success() throws BookingException, SlotException, UserException {
        when(userRepository.findById("user1")).thenReturn(Optional.of(testUser));
        when(centerRepository.findById("1")).thenReturn(Optional.of(testCenter));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArgument(0));

        Booking result = bookingService.bookSlot("1", "user1", "slot1");

        assertNotNull(result);
        assertEquals("user1", result.getUserId());
        assertEquals(4, testSlot.getAvailableSeats()); // Seat count should decrease by 1
        assertTrue(testSlot.getBookedUsers().contains("user1"));
    }

    @Test
    void bookSlot_UserNotFound() {
        when(userRepository.findById("user1")).thenReturn(Optional.empty());

        assertThrows(UserException.class, () ->
                bookingService.bookSlot("1", "user1", "slot1"));
    }

    @Test
    void bookSlot_NoSeatsAvailable() {
        testSlot.setAvailableSeats(0);
        when(userRepository.findById("user1")).thenReturn(Optional.of(testUser));
        when(centerRepository.findById("1")).thenReturn(Optional.of(testCenter));

        assertThrows(BookingException.class, () ->
                bookingService.bookSlot("1", "user1", "slot1"));
    }

    @Test
    void cancelBooking_Success() throws BookingException {
        Booking booking = new Booking("book1", "user1", "1", "slot1", new Date(), BookingStatus.CONFIRMED);
        when(bookingRepository.findById("book1")).thenReturn(Optional.of(booking));
        when(centerRepository.findById("1")).thenReturn(Optional.of(testCenter));

        bookingService.cancelBooking("book1");

        assertEquals(6, testSlot.getAvailableSeats()); // Seat count should increase by 1
        assertFalse(testSlot.getBookedUsers().contains("user1"));
    }
}