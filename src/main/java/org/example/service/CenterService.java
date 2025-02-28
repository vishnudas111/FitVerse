package org.example.service;

import org.example.model.Center;
import org.example.model.Slot;
import org.example.model.Workout;
import org.example.repository.CenterRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CenterService {

    public void addCenter(String name, String city, List<String> daysClosed, int numberOfSlots){
        Center center = new Center();
        center.setName(name);
        center.setCity(city);
        center.setDaysClosed(daysClosed);
        center.setNumberOfSlots(numberOfSlots);
        CenterRepository.addCenter(center);
        System.out.println("Center " + name + " added to city : " + city);
    }
    public void addWorkoutType(String centerName, String workoutType){
        Center center = CenterRepository.getCenter(centerName);
        if(center!=null){
            Workout workout = new Workout();
            workout.setName(workoutType);
            workout.setCenterName(centerName);
            center.getWorkouts().add(workout);
        }
        System.out.println("Workout Type " + workoutType + " added to center : " + centerName);
    }
    public void addSlot(String centerName, String workoutType, String startTime, int numberOfSeats){
        Center center = CenterRepository.getCenter(centerName);
        if(center!=null){
            Slot slot = new Slot();
            slot.setSlotId(String.valueOf(CenterRepository.getNextSlotId(centerName)));
            slot.setCenterName(centerName);
            slot.setWorkoutType(workoutType);
            slot.setStartTime(startTime);
            slot.setAvailableSeats(numberOfSeats);
            center.getSlots().add(slot);
        }
        System.out.println("Slot added for " + workoutType + " to center : " + centerName);
    }

    public List<Map<String, String>> getAvailableSlot(String centerName, String date) {
        Center center = CenterRepository.getCenter(centerName);
        if (center != null) {
            return center.getSlots().stream()
                    .filter(slot -> slot.getAvailableSeats() > 0)
                    .map(slot -> {
                        Map<String, String> slotDetails = new HashMap<>();
                        slotDetails.put("slotId", slot.getSlotId());
                        slotDetails.put("centerName", centerName);
                        slotDetails.put("workoutType", slot.getWorkoutType());
                        slotDetails.put("slotTime", slot.getStartTime());
                        slotDetails.put("availableSeats", String.valueOf(slot.getAvailableSeats()));
                        return slotDetails;
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
