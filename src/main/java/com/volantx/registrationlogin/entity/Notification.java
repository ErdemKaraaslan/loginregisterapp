package com.volantx.registrationlogin.entity;

import com.volantx.registrationlogin.controller.dto.UserDto;
import com.volantx.registrationlogin.enums.Gender;
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
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "content")
    private String content;

    @Column(name = "sent_time")
    private LocalDateTime sentTime;

}
