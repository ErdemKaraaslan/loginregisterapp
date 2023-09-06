package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.SaveMessageDto;
import com.volantx.registrationlogin.controller.dto.SentMessageDto;
import com.volantx.registrationlogin.controller.mapper.MessageMapper;
import com.volantx.registrationlogin.controller.mapper.SentMessageMapper;
import com.volantx.registrationlogin.controller.resource.MessageResource;
import com.volantx.registrationlogin.controller.resource.SentMessageResource;
import com.volantx.registrationlogin.entity.SentMessage;
import com.volantx.registrationlogin.service.SentMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sent-messages")
public class SentMessageController {

    private final SentMessageService service;
    private final SentMessageMapper mapper;
    private final MessageMapper messageMapper;


    @GetMapping()
    public List<SentMessageResource> getAllMessages(){
        return mapper.modelsToResources(service.getAllSentMessages());
    }

    @GetMapping("/{id}")
    public SentMessageResource getOneSentMessageById(@PathVariable Long id) {
        return mapper.modelToResource(service.getOneSentMessageById(id));
    }

    @GetMapping("/user/{id}")
    public List<SentMessageResource> getAllSentMessagesByUser(@PathVariable Long id){
        return mapper.modelsToResources(service.findSentMessageByUserId(id));
    }

    @GetMapping("/users/{sender-id}/{receiver-id}")
    public List<MessageResource> getSpecificMessages(@PathVariable("sender-id") Long senderId,
                                                         @PathVariable("receiver-id") Long receiverId){
        return messageMapper.modelsToResources(service.getSpecificMessages(senderId,receiverId));
    }

    @PostMapping()
    public SentMessageResource saveSentMessage(@RequestBody SentMessageDto sentMessageDto) {
        SentMessage sentMessage = mapper.dtoToModel(sentMessageDto);
        return mapper.modelToResource(service.saveSentMessage(sentMessage));
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody SaveMessageDto dto) throws Exception {
        service.sendMessage(dto);
    }




}
