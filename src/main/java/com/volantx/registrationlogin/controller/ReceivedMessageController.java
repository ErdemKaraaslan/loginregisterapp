package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.MessageDto;
import com.volantx.registrationlogin.controller.dto.ReceivedMessageDto;
import com.volantx.registrationlogin.controller.mapper.MessageMapper;
import com.volantx.registrationlogin.controller.mapper.ReceivedMessageMapper;
import com.volantx.registrationlogin.controller.resource.MessageResource;
import com.volantx.registrationlogin.controller.resource.NotificationResource;
import com.volantx.registrationlogin.controller.resource.ReceivedMessageResource;
import com.volantx.registrationlogin.controller.resource.SentMessageResource;
import com.volantx.registrationlogin.entity.Message;
import com.volantx.registrationlogin.entity.Notification;
import com.volantx.registrationlogin.entity.ReceivedMessage;
import com.volantx.registrationlogin.service.MessageService;
import com.volantx.registrationlogin.service.ReceivedMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receivedMessages")
public class ReceivedMessageController {

    private final ReceivedMessageService service;
    private final ReceivedMessageMapper mapper;


    @GetMapping()
    public List<ReceivedMessageResource> getAllMessages(){
        return mapper.modelsToResources(service.getAllReceivedMessages());
    }

    @GetMapping("/{id}")
    public ReceivedMessageResource getOneReceivedMessageById(@PathVariable String id) {
        return mapper.modelToResource(service.getOneReceivedMessageById(id));
    }

    @GetMapping("/user/{id}")
    public List<ReceivedMessageResource> getAllReceivedMessagesByUser(@PathVariable Long id){
        return mapper.modelsToResources(service.getAllReceivedMessagesByUser(id));
    }

    @PostMapping()
    public ReceivedMessageResource saveReceivedMessage(@RequestBody ReceivedMessageDto receivedMessageDto) {
        ReceivedMessage receivedMessage = mapper.dtoToModel(receivedMessageDto);
        return mapper.modelToResource(service.saveReceivedMessage(receivedMessage));
    }




}
