package com.fitverse.entity;

// Booking.java

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "center_id", referencedColumnName = "centerId")
    private Center center;

    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "slotId")
    private Slot slot;

    private Date bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
