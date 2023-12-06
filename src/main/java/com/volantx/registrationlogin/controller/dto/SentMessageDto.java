package com.volantx.registrationlogin.controller.dto;

import lombok.Data;

@Data
public class SentMessageDto {

    private Long id;

    private Long userId;

    private Long messageId;

    private boolean isDeleted;
}
