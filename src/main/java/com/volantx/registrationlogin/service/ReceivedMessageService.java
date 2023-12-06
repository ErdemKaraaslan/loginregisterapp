package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.controller.dto.ReceivedMessageDto;
import com.volantx.registrationlogin.entity.ReceivedMessage;
import com.volantx.registrationlogin.repository.ReceivedMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceivedMessageService {

    private final ReceivedMessageRepository repository;


    public List<ReceivedMessage> getAllReceivedMessages() {

        return repository.findAll();

    }

    public ReceivedMessage getOneReceivedMessageById(String id) {
        return repository.findById(id).get();
    }

    public ReceivedMessage saveReceivedMessage(ReceivedMessage receivedMessage) {

        return repository.save(receivedMessage);

    }

    public List<ReceivedMessage> getAllReceivedMessagesByUser(Long id) {
        return repository.getAllReceivedMessagesByUser(id);
    }
}
