package com.volantx.registrationlogin.entity;

import com.volantx.registrationlogin.enums.NotificationType;
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
@Table(name = "notifications")
public class Notification extends BaseEntity {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "notification_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_user")
    private User receiverUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "causing_user")
    private User causingUser;

    @Column(name = "content")
    private String content;

    @Column(name = "sent_time")
    private LocalDateTime sentTime;

}
