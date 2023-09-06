package com.volantx.registrationlogin.controller.resource;

import com.volantx.registrationlogin.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Gender gender;

    private String about;
}
