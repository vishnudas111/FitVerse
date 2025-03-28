package com.fitverse.dao;

// CenterDao.java

import com.fitverse.entity.Center;

import java.util.List;
import java.util.Optional;

public interface CenterDao {
    void addCenter(Center center);
    Optional<Center> getCenter(String centerId);
    List<Center> getAllCenters();
    List<Center> getCentersByCity(String city);
}