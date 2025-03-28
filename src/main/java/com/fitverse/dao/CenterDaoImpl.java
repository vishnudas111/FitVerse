package com.fitverse.dao;

// CenterDaoImpl.java

import com.fitverse.entity.Center;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class CenterDaoImpl implements CenterDao {
    private final Map<String, Center> centers = new HashMap<>();

    @Override
    public void addCenter(Center center) {
        log.debug("Adding new center: {}", center.getName());
        centers.put(center.getCenterId(), center);
    }

    @Override
    public Optional<Center> getCenter(String centerId) {
        log.debug("Fetching center with ID: {}", centerId);
        return Optional.ofNullable(centers.get(centerId));
    }

    @Override
    public List<Center> getAllCenters() {
        log.debug("Fetching all centers");
        return new ArrayList<>(centers.values());
    }

    @Override
    public List<Center> getCentersByCity(String city) {
        log.debug("Fetching centers in city: {}", city);
        return centers.values().stream()
                .filter(center -> center.getCity().equalsIgnoreCase(city))
                .toList();
    }
}
