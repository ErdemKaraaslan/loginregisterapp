package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.controller.dto.SaveMessageDto;
import com.volantx.registrationlogin.entity.Message;
import com.volantx.registrationlogin.entity.ReceivedMessage;
import com.volantx.registrationlogin.entity.SentMessage;
import com.volantx.registrationlogin.repository.SentMessageRepository;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SentMessageService {

    private final SentMessageRepository repository;
    private final UserService userService;
    private final MessageService messageService;
    private final ReceivedMessageService receivedMessageService;


    public List<SentMessage> getAllSentMessages() {

        return repository.findAll();

    }

    public SentMessage getOneSentMessageById(Long id) {
        return repository.findById(id).get();
    }

    public SentMessage saveSentMessage(SentMessage sentMessage) {

        return repository.save(sentMessage);

    }

    public void sendMessage(SaveMessageDto dto) throws Exception {

        if (!userService.checkUser(dto.getSenderId()) || !userService.checkUser(dto.getReceiverId())) {
            throw new Exception("sender user or receiver user doesnt exist");
        }
        Message message = new Message(dto.getMessageContent(), LocalDateTime.now());
        messageService.saveMessage(message);

        SentMessage sentMessage = new SentMessage(userService.getOneUserById(dto.getSenderId()), message, false);
        repository.save(sentMessage);

        ReceivedMessage receivedMessage = new ReceivedMessage(userService.getOneUserById(dto.getReceiverId()), message,
                false, false, LocalDateTime.now());
        receivedMessageService.saveReceivedMessage(receivedMessage);
        //ToDo: notification eklenecek.
    }

    public List<Message> getSpecificMessages(Long senderId, Long receiverId) {
        List<Tuple> specificMessages = repository.getSpecificMessages(senderId, receiverId);
        List<Message> messages = specificMessages.stream()
                .map(tuple -> new Message((Long) tuple.get(0), (String) tuple.get(1), ((Timestamp) tuple.get(2)).toLocalDateTime()))
                .collect(Collectors.toList());
        return messages;
    }


    public List<SentMessage> findSentMessageByUserId(Long id) {
        return repository.findByUser_Id(id);
    }
}

