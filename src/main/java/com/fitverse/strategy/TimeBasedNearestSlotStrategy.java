package com.fitverse.strategy;

// SlotRecommendationStrategy.java

import com.fitverse.entity.Slot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TimeBasedNearestSlotStrategy implements NearestSlotStrategy {
    @Override
    public Optional<Slot> recommendNearestSlot(List<Slot> availableSlots, String currentTime) {
        return availableSlots.stream()
                .filter(slot -> slot.getTime().compareTo(currentTime) >= 0)
                .min(Comparator.comparing(Slot::getTime));
    }
}