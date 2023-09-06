package com.volantx.registrationlogin.controller.dto;

import lombok.Data;

@Data
public class SaveMessageDto {
    private Long senderId;
    private Long receiverId;
    private String messageContent;
}
