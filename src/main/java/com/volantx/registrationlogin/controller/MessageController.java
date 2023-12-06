package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.MessageDto;
import com.volantx.registrationlogin.controller.dto.UserDto;
import com.volantx.registrationlogin.controller.mapper.MessageMapper;
import com.volantx.registrationlogin.controller.resource.MessageResource;
import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.service.MessageService;
import com.volantx.registrationlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;
    private final UserService userService;
    private final MessageMapper mapper;

    @GetMapping()
    public List<MessageResource> getAllMessages() {
        return mapper.modelsToResources(service.getAllMessages());
    }

    @GetMapping("/{id}")
    public MessageResource getOneMessageById(@PathVariable String id) {
        return mapper.modelToResource(service.getOneMessageById(id));
    }

    @PostMapping()
    public MessageResource saveMessage(@RequestBody MessageDto messageDto) {
        return mapper.modelToResource(service.saveMessage(mapper.dtoToModel(messageDto)));
    }

    @PutMapping("/update")
    public MessageResource updateMessage(@RequestBody MessageDto messageDto) {
        return mapper.modelToResource(service.updateMessage(mapper.dtoToModel(messageDto)));
    }


    //@Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteMessageById(@PathVariable String id) {
        service.deleteMessageById(id);
    }


}
