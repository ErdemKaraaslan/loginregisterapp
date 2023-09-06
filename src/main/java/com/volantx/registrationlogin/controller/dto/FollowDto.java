package com.volantx.registrationlogin.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {

    //private Long id;
    private Long followerId;
    private Long followingId;
    //private LocalDateTime followTime;
}