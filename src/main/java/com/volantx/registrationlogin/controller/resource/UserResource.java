package com.volantx.registrationlogin.controller.resource;

import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.Notification;
import com.volantx.registrationlogin.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Gender gender;

    private String about;

   // private List<Follow> followers;

   // private List<Follow> followings;

   // private List<Notification> notifications;

}
