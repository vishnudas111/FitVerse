package com.fitverse.repository;

// BookingRepository.java

import com.fitverse.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByUserId(String userId);
    List<Booking> findByCenterIdAndSlotId(String centerId, String slotId);
}