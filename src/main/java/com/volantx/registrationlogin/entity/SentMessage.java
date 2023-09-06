package com.volantx.registrationlogin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sent_messages")
public class SentMessage {

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

    public SentMessage(User user, Message message, boolean isDeleted) {
        this.user = user;
        this.message = message;
        this.isDeleted = isDeleted;
    }
}
