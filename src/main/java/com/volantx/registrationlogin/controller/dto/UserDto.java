package com.volantx.registrationlogin.controller.dto;

import com.volantx.registrationlogin.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Gender gender;

    private String about;

    private String password;
}
