package com.fitverse.entity;

// Slot.java

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Table(name = "slots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot {
    @Id
    private String slotId;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    private String workoutType;
    private String time;
    private int totalSeats;
    private int availableSeats;

    @ElementCollection
    @CollectionTable(name = "slot_booked_users", joinColumns = @JoinColumn(name = "slot_id"))
    @Column(name = "user_id")
    private List<String> bookedUsers = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "waitlist_id", referencedColumnName = "id")
    private Waitlist waitlist;

    @Transient
    private transient ReentrantLock lock = new ReentrantLock();
}