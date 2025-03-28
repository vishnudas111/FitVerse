package com.fitverse.controller;


import com.fitverse.entity.*;
import com.fitverse.exception.*;
import com.fitverse.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @Test
    void bookSlot_Success() throws BookingException, SlotException, UserException {
        Booking expectedBooking = new Booking("book1", "user1", "center1", "slot1", new Date(), BookingStatus.CONFIRMED);
        when(bookingService.bookSlot("center1", "user1", "slot1")).thenReturn(expectedBooking);

        ResponseEntity<Booking> response = bookingController.bookSlot("center1", "user1", "slot1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedBooking, response.getBody());
    }

    @Test
    void bookSlot_UserException() throws BookingException, SlotException, UserException {
        when(bookingService.bookSlot("center1", "user1", "slot1"))
                .thenThrow(new UserException("User not found"));

        assertThrows(UserException.class, () ->
                bookingController.bookSlot("center1", "user1", "slot1"));
    }

    @Test
    void getUserBookings_Success() {
        List<Booking> expectedBookings = List.of(
                new Booking("book1", "user1", "center1", "slot1", new Date(), BookingStatus.CONFIRMED)
        );
        when(bookingService.getUserBookings("user1")).thenReturn(expectedBookings);

        ResponseEntity<List<Booking>> response = bookingController.getUserBookings("user1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
}
