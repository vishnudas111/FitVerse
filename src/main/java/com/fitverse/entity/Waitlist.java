package com.fitverse.entity;

// Waitlist.java

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "waitlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Waitlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int maxSize;

    @ElementCollection
    @CollectionTable(name = "waitlist_users", joinColumns = @JoinColumn(name = "waitlist_id"))
    @Column(name = "user_id")
    private List<String> userIds = new ArrayList<>();

    public boolean addUser(String userId) {
        if (userIds.size() < maxSize) {
            userIds.add(userId);
            return true;
        }
        return false;
    }

    public String removeNextUser() {
        if (!userIds.isEmpty()) {
            return userIds.remove(0);
        }
        return null;
    }
}
