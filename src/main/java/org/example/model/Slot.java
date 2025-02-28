package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Slot {
    private String slotId;
    private String centerName;
    private String workoutType;
    private String startTime;
    private int availableSeats;
    private List<String> waitList = new ArrayList<>();
}
