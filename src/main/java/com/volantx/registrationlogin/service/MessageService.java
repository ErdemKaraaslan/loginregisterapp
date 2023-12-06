package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.controller.dto.MessageDto;
import com.volantx.registrationlogin.entity.Message;
import com.volantx.registrationlogin.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public List<Message> getAllMessages() {

        return repository.findAll();

    }

    public Message getOneMessageById(String id) {
        return repository.findById(id).get();
    }

    /*public Message saveMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setCreateTime(messageDto.getCreateTime());
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContent());
        return repository.save(message);

    }*/

    public Message saveMessage(Message message){
        return repository.save(message);
    }

    public Message updateMessage(Message message) {
        /*Message message = repository.findById(messageDto.getId()).orElse(null);
        message.setContent(messageDto.getContent());
        message.setId(message.getId());
        message.setCreateTime(messageDto.getCreateTime());*/
        return repository.save(message);
    }

    public void deleteMessageById(String id) {
        repository.deleteById(id);
    }



}
