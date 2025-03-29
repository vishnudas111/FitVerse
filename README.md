# 🏋️ Fitverse - Workout Slot Booking System

FlipFit is a scalable backend system designed to manage gym slot bookings across multiple centers in Bangalore. The system allows users to register, view available workout slots, book or cancel workout sessions, and get recommendations for nearby available slots. It also handles concurrent bookings, prevents overbooking, and manages a waitlist effectively.

---

## 🎯 **Project Overview**
- **Goal:** Build a backend system to manage workout slot reservations across fitness centers, ensuring concurrent slot booking with minimal latency.
- **Tech Stack:**
  - **Backend:** Node.js, Express.js
  - **Database:** MongoDB, Redis (for caching and concurrency management)
  - **Authentication:** JWT for session management
  - **API Design:** RESTful APIs with well-structured endpoints

---

## 🚀 **Key Features**
- **Multi-Center and Multi-City Expansion:**  
  Supports adding multiple centers dynamically with varying workout types, slots, and seat capacities. Can easily scale to multiple cities.
  
- **Slot Management & Booking System:**  
  - Concurrent blocking to prevent double-booking.
  - Manages multiple workout variations like weights, cardio, yoga, etc.
  - Handles cancellations with automatic waitlist promotion.

- **Waitlist Management:**  
  - Maintains a waitlist for fully booked slots.
  - Notifies users upon promotion from the waitlist.

- **Slot Recommendations:**  
  Suggests the nearest available time slot for the same workout/center in case of unavailability.

---

## 📚 **API Endpoints**
### 1. **Center Management**
- `POST /addCenter` - Add a new fitness center.
- `POST /addWorkOutType` - Add workout types to a center.
- `POST /addSlots` - Define time slots for workouts.

### 2. **User Management**
- `POST /registerUser` - Register a new user.
- `GET /getAvailableSlot` - View available slots for a center.
- `POST /BookSlot` - Book a slot for a user.
- `GET /viewUserBooking` - View user’s booked slots for a date.
- `DELETE /cancelSlot` - Cancel a booking.

### 3. **Bonus APIs**
- `GET /recommendSlot` - Recommend the nearest available slot.
- `POST /addToWaitList` - Add a user to the waitlist.

---

## 🧩 **System Design Highlights**
- **Concurrency Control:** Prevents double-booking using atomic slot locking with Redis.
- **Waitlist and Promotion Logic:** Manages waitlisted users and promotes them automatically when slots become available.
- **Scalable Architecture:** Supports easy transition to different databases as the system scales without impacting core business logic.

---

## 🔥 **Setup Instructions**
1. **Clone the Repository:**
```bash
git clone https://github.com/yourusername/flipfit.git
cd flipfit
