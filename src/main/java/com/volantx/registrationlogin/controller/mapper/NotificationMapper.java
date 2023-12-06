package com.volantx.registrationlogin.controller.mapper;

import com.volantx.registrationlogin.controller.dto.MessageDto;
import com.volantx.registrationlogin.controller.dto.NotificationDto;
import com.volantx.registrationlogin.controller.resource.MessageResource;
import com.volantx.registrationlogin.controller.resource.NotificationResource;
import com.volantx.registrationlogin.entity.Message;
import com.volantx.registrationlogin.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationResource modelToResource(final Notification notification);

    List<NotificationResource> modelsToResources(final List<Notification> notificationList);

    @Mapping(source = "receiverUserId", target = "receiverUser.id")
    Notification dtoToModel(final NotificationDto notificationDto);
}