package com.volantx.registrationlogin.entity;

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
@Table(name = "received_messages")
public class ReceivedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;

    @Column(name = "is_deleted", nullable=false)
    private boolean isDeleted;

    @Column(name = "is_seen", nullable=false)
    private boolean isSeen;

    @Column(name = "seen_time")
    private LocalDateTime seenTime;

    public ReceivedMessage(User user, Message message, boolean isDeleted, boolean isSeen, LocalDateTime seenTime) {
        this.user = user;
        this.message = message;
        this.isDeleted = isDeleted;
        this.isSeen = isSeen;
        this.seenTime = seenTime;
    }
}
