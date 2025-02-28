package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {

    private String bookingId;
    private String userId;
    private String slotId;
    private String date;
    private String centerName;
    private String workoutType;
}
