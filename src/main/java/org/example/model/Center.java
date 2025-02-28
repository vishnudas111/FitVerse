package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Center {

    private String name;
    private String city;
    private int numberOfSlots;
    private List<String> daysClosed;
    private List<Slot> slots = new ArrayList<>();
    private List<Workout> workouts = new ArrayList<>();

}
