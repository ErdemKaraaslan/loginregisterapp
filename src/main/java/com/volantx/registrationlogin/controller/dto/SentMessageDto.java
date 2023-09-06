package com.volantx.registrationlogin.controller.dto;

import com.volantx.registrationlogin.controller.resource.MessageResource;
import com.volantx.registrationlogin.controller.resource.UserResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SentMessageDto {

    //private Long id;
    private Long userId;
    private Long messageId;
    //private boolean isDeleted;

}

