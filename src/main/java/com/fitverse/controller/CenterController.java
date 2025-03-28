package com.fitverse.controller;

// CenterController.java


import com.fitverse.entity.Center;
import com.fitverse.exception.CenterException;
import com.fitverse.service.CenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@Slf4j
public class CenterController {
    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @PostMapping
    public Center addCenter(@RequestBody Center center) {
        log.info("Adding new center: {}", center.getName());
        return centerService.addCenter(center);
    }

    @GetMapping("/{centerId}")
    public Center getCenter(@PathVariable String centerId) throws CenterException {
        log.info("Fetching center with ID: {}", centerId);
        return centerService.getCenter(centerId);
    }

    @GetMapping
    public List<Center> getAllCenters() {
        log.info("Fetching all centers");
        return centerService.getAllCenters();
    }

    @GetMapping("/city/{city}")
    public List<Center> getCentersByCity(@PathVariable String city) {
        log.info("Fetching centers in city: {}", city);
        return centerService.getCentersByCity(city);
    }
}