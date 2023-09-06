package com.volantx.registrationlogin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    @Column(name = "follow_time")
    private LocalDateTime followTime;

    public Follow(User follower, User following, LocalDateTime followTime) {
        this.follower = follower;
        this.following = following;
        this.followTime = followTime;
    }
}
