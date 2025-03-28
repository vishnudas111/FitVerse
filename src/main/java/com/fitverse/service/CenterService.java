package com.fitverse.service;

// CenterService.java

import com.fitverse.dao.CenterDao;
import com.fitverse.entity.Center;
import com.fitverse.exception.CenterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CenterService {
    private final CenterDao centerDao;

    public CenterService(CenterDao centerDao) {
        this.centerDao = centerDao;
    }

    public Center addCenter(Center center) {
        log.info("Adding center: {}", center.getName());
        centerDao.addCenter(center);
        return center;
    }

    public Center getCenter(String centerId) throws CenterException {
        log.info("Fetching center: {}", centerId);
        return centerDao.getCenter(centerId)
                .orElseThrow(() -> new CenterException("Center not found"));
    }

    public List<Center> getAllCenters() {
        log.info("Fetching all centers");
        return centerDao.getAllCenters();
    }

    public List<Center> getCentersByCity(String city) {
        log.info("Fetching centers in city: {}", city);
        return centerDao.getCentersByCity(city);
    }
}