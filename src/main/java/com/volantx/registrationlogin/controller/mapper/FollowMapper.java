package com.volantx.registrationlogin.controller.mapper;

import com.volantx.registrationlogin.controller.dto.FollowDto;
import com.volantx.registrationlogin.controller.resource.FollowResource;
import com.volantx.registrationlogin.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface FollowMapper {

    FollowResource modelToResource(final Follow follow);

    List<FollowResource> modelsToResources(final List<Follow> followList);

    @Mapping(source = "senderId", target = "follower.id")
    @Mapping(source = "receiverId", target = "following.id")
    Follow dtoToModel(final FollowDto followDto);
}