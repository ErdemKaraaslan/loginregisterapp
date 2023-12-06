package com.volantx.registrationlogin.controller.resource;

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
public class FollowResource {

    private String id;
    private UserResource follower;
    private UserResource following;
    private LocalDateTime followTime;


}
