package com.volantx.registrationlogin.controller.mapper;

import com.volantx.registrationlogin.controller.dto.MessageDto;
import com.volantx.registrationlogin.controller.dto.ReceivedMessageDto;
import com.volantx.registrationlogin.controller.resource.MessageResource;
import com.volantx.registrationlogin.controller.resource.ReceivedMessageResource;
import com.volantx.registrationlogin.entity.Message;
import com.volantx.registrationlogin.entity.ReceivedMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceivedMessageMapper {

    ReceivedMessageResource modelToResource(final ReceivedMessage receivedMessage);

    List<ReceivedMessageResource> modelsToResources(final List<ReceivedMessage> receivedMessage);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "messageId", target = "message.id")
    ReceivedMessage dtoToModel(final ReceivedMessageDto receivedMessageDto);
}