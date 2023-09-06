package com.volantx.registrationlogin.controller.resource;

import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.enums.FollowRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequestResource {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private FollowRequestStatus requestStatus;
    private LocalDateTime requestTime;


}
