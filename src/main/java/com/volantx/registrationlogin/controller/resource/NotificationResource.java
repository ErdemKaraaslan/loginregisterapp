package com.volantx.registrationlogin.controller.resource;

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

    private String id;
    private NotificationType notificationType;
    private UserResource causingUser;
    private UserResource receiverUser;
    private String content;
    private LocalDateTime sentTime;


}
