package com.volantx.registrationlogin.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SentMessageResource {

    private String id;
    private UserResource user;
    private MessageResource message;
    private boolean isDeleted;

}
