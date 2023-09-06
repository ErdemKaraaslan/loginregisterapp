package com.volantx.registrationlogin.controller.resource;

import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResource {

    private Long id;
    private NotificationType notificationType;
    private UserResource user;
    private String content;
    private LocalDateTime sentTime;


}
