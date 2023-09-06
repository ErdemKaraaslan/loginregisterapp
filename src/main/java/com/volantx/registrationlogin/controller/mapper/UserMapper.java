package com.volantx.registrationlogin.controller.mapper;

import com.volantx.registrationlogin.controller.dto.UserDto;
import com.volantx.registrationlogin.controller.resource.UserResource;
import com.volantx.registrationlogin.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResource modelToResource (final User user);

    List<UserResource> modelsToResources (final List<User> userList);

    User dtoToModel (final UserDto userDto);
}
