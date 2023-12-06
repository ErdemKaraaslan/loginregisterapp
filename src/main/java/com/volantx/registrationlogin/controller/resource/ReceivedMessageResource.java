package com.volantx.registrationlogin.controller.resource;

import com.volantx.registrationlogin.entity.Message;
import com.volantx.registrationlogin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedMessageResource {

    private String id;
    private UserResource user;
    private MessageResource message;
    private boolean isDeleted;
    private boolean isSeen;
    private LocalDateTime seenTime;

}
