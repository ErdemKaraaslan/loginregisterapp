package com.volantx.registrationlogin.controller.mapper;

import com.volantx.registrationlogin.controller.dto.FollowDto;
import com.volantx.registrationlogin.controller.dto.FollowRequestDto;
import com.volantx.registrationlogin.controller.resource.FollowRequestResource;
import com.volantx.registrationlogin.controller.resource.FollowResource;
import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.FollowRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface FollowRequestMapper {

    @Mapping(source = "senderId.id", target = "senderId")
    @Mapping(source = "receiverId.id", target = "receiverId")
    FollowRequestResource modelToResource(final FollowRequest followRequest);

    List<FollowRequestResource> modelsToResources(final List<FollowRequest> followRequestList);
    @Mapping(source = "senderId", target = "senderId.id")
    @Mapping(source = "receiverId", target = "receiverId.id")
    FollowRequest dtoToModel(final FollowRequestDto followRequestDto);
}