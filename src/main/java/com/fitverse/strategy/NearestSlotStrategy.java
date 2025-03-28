package com.fitverse.strategy;

// NearestSlotStrategy.java

import com.fitverse.entity.Slot;

import java.util.List;
import java.util.Optional;

public interface NearestSlotStrategy {
    Optional<Slot> recommendNearestSlot(List<Slot> availableSlots, String currentTime);
}
