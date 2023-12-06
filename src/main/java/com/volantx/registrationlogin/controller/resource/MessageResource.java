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
public class MessageResource {

    private String id;
    private String content;
    private LocalDateTime createTime;
    private Long senderId;
    private Long receiverId;
}
