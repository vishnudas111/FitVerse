package com.fitverse.service;

// NotificationService.java


import com.fitverse.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public void notifyUser(User user, String message) {
        log.info("Notification sent to {} ({}): {}", user.getName(), user.getUserId(), message);
    }

    public void notifyWaitlistPromotion(User user, String centerName, String slotTime, String workoutType) {
        String message = String.format(
                "Congratulations! You've been promoted from waitlist for %s at %s (%s)",
                workoutType, centerName, slotTime
        );
        notifyUser(user, message);
    }
}