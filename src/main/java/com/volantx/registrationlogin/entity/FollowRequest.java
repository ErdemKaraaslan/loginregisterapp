package com.volantx.registrationlogin.entity;

import com.volantx.registrationlogin.enums.FollowRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "follow_requests")
public class FollowRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User senderId;    //sender

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverId;          //receiver

    @Column(name = "follow_request_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FollowRequestStatus requestStatus;

    @Column(name = "request_time")
    private LocalDateTime requestTime;

    public FollowRequest(User senderId, User receiverId, FollowRequestStatus requestStatus, LocalDateTime requestTime) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.requestStatus = requestStatus;
        this.requestTime = requestTime;
    }
}
