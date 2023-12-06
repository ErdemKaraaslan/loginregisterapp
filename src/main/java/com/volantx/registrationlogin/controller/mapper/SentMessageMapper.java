package com.volantx.registrationlogin.controller.mapper;

import com.volantx.registrationlogin.controller.dto.SentMessageDto;
import com.volantx.registrationlogin.controller.resource.SentMessageResource;
import com.volantx.registrationlogin.entity.SentMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SentMessageMapper {

    SentMessageResource modelToResource(final SentMessage sentMessage);

    List<SentMessageResource> modelsToResources(final List<SentMessage> sentMessages);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "messageId", target = "message.id")
    SentMessage dtoToModel(final SentMessageDto sentMessageDto);
}