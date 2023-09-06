package com.volantx.registrationlogin.controller.mapper;

import com.volantx.registrationlogin.controller.dto.MessageDto;
import com.volantx.registrationlogin.controller.resource.MessageResource;
import com.volantx.registrationlogin.entity.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageResource modelToResource(final Message message);

    List<MessageResource> modelsToResources(final List<Message> messageList);

    Message dtoToModel(final MessageDto messageDto);
}