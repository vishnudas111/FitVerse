package com.fitverse;

// FlipFitApplication.java

import com.fitverse.controller.*;
import com.fitverse.dao.*;
import com.fitverse.service.*;
import com.fitverse.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class FitVerseApplication {
    public static void main(String[] args) {
        log.info("Starting FlipFit Application");
        SpringApplication.run(FitVerseApplication.class, args);
    }

    @Bean
    public CenterDao centerDao() {
        return new CenterDaoImpl();
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public WorkoutDao workoutDao() {
        return new WorkoutDaoImpl();
    }

    @Bean
    public BookingDao bookingDao() {
        return new BookingDaoImpl();
    }

    @Bean
    public CenterService centerService(CenterDao centerDao) {
        return new CenterService(centerDao);
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }

    @Bean
    public WorkoutService workoutService(WorkoutDao workoutDao, CenterDao centerDao) {
        return new WorkoutService(workoutDao, centerDao);
    }

    @Bean
    public NotificationService notificationService() {
        return new NotificationService();
    }

    @Bean
    public BookingService bookingService(BookingDao bookingDao, CenterDao centerDao,
                                         UserDao userDao, NotificationService notificationService) {
        return new BookingService(bookingDao, centerDao, userDao, notificationService);
    }

    @Bean
    public CenterController centerController(CenterService centerService) {
        return new CenterController(centerService);
    }

    @Bean
    public UserController userController(UserService userService) {
        return new UserController(userService);
    }

    @Bean
    public WorkoutController workoutController(WorkoutService workoutService) {
        return new WorkoutController(workoutService);
    }

    @Bean
    public BookingController bookingController(BookingService bookingService) {
        return new BookingController(bookingService);
    }

    @Bean
    public NearestSlotStrategy nearestSlotStrategy() {
        return new TimeBasedNearestSlotStrategy();
    }
}