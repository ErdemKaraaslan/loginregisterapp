package com.volantx.registrationlogin.mapper;

import com.volantx.registrationlogin.dto.UserDto;
import com.volantx.registrationlogin.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto modelToDto (final User user);

    List<UserDto> modelsToDtos (final List<User> userList);

    User dtoToModel (final UserDto userDto);
}
