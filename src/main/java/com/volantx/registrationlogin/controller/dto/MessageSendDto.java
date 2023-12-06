package com.volantx.registrationlogin.controller.dto;

import lombok.Data;

@Data
public class MessageSendDto {

    private Long senderId;
    private Long receiverId;
    private String messageContent;
}
