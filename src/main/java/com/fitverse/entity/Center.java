package com.fitverse.entity;

// Center.java

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "centers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Center {
    @Id
    private String centerId;
    private String name;
    private String city;

    @ElementCollection
    @CollectionTable(name = "center_days_closed", joinColumns = @JoinColumn(name = "center_id"))
    @Column(name = "day")
    private List<String> daysClosed = new ArrayList<>();

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Slot> slots = new ArrayList<>();

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutType> workoutTypes = new ArrayList<>();

    public void addSlot(Slot slot) {
        slots.add(slot);
        slot.setCenter(this);
    }

    public void addWorkoutType(WorkoutType workoutType) {
        workoutTypes.add(workoutType);
        workoutType.setCenter(this);
    }
}