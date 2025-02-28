package org.example.repository;

import org.example.model.Center;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CenterRepository {
    private static Map<String, Center> centers = new ConcurrentHashMap<>();
    private static Map<String, Integer> slotCounter = new ConcurrentHashMap<>();  //Keeping counter for slots for center

    public static void addCenter(Center center) {
        centers.put(center.getName(), center);
    }

    public static Center getCenter(String name) {
        return centers.getOrDefault(name,new Center());
    }
    public static int getNextSlotId(String centerName) {
        int nextId = slotCounter.getOrDefault(centerName, 0) + 1;
        slotCounter.put(centerName, nextId); // Update the counter
        return nextId;
    }
}
