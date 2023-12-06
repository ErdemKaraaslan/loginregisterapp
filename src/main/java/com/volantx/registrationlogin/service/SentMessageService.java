package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.controller.dto.MessageSendDto;
import com.volantx.registrationlogin.controller.resource.MessageResource;
import com.volantx.registrationlogin.entity.Message;
import com.volantx.registrationlogin.entity.ReceivedMessage;
import com.volantx.registrationlogin.entity.SentMessage;
import com.volantx.registrationlogin.repository.SentMessageRepository;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public SentMessage getOneSentMessageById(String id) {
        return repository.findById(id).get();
    }

    public SentMessage saveSentMessage(SentMessage sentMessage) {

        return repository.save(sentMessage);

    }

    public void sendMessage(MessageSendDto dto) throws Exception {

        if (!userService.checkUser(dto.getSenderId()) || !userService.checkUser(dto.getReceiverId())) {
            throw new Exception("sender user or receiver user doesnt exist");
        }
        Message message = new Message();
        message.setContent(dto.getMessageContent());
        message.setCreateTime(LocalDateTime.now());

        messageService.saveMessage(message);

        SentMessage sentMessage = new SentMessage();
        sentMessage.setUser(userService.getOneUserById(dto.getSenderId().toString()));
        sentMessage.setMessage(message);
        sentMessage.setDeleted(false);

        repository.save(sentMessage);



        ReceivedMessage receivedMessage = new ReceivedMessage();
        receivedMessage.setUser(userService.getOneUserById(dto.getReceiverId().toString()));
        receivedMessage.setMessage(message);
        receivedMessage.setSeen(false);
        receivedMessage.setDeleted(false);
        receivedMessage.setSeenTime(LocalDateTime.now());

        receivedMessageService.saveReceivedMessage(receivedMessage);
        //ToDo: notification eklenecek.
    }

    public List<MessageResource> getAllMessagesBetweenTwoPerson(String senderId, String receiverId) {

        List<Tuple> allMessages = repository.getAllMessagesBetweenTwoPerson(receiverId, senderId);

        return allMessages.stream()
                .map(tuple -> new MessageResource(
                        (String) tuple.get(0),
                        (String) tuple.get(1),
                        ((Timestamp) tuple.get(2)).toLocalDateTime(),
                        (Long) tuple.get(3),
                        (Long) tuple.get(4)))
                .collect(Collectors.toList());
    }

    public List<Message> getSpecificMessages(String senderId, String receiverId) {

        List<Tuple> specificMessages = repository.getSpecificMessages(senderId, receiverId);

        List<Message> messages = specificMessages.stream()
                .map(tuple -> {
                    Message message = new Message();
                    message.setId(String.valueOf((Long) tuple.get(0)));
                    message.setContent((String) tuple.get(1));
                    message.setCreateTime(((Timestamp) tuple.get(2)).toLocalDateTime());

                    return message;
                })
                .collect(Collectors.toList());

        return messages;
    }




    public List<SentMessage> findSentMessageByUserId(String id) {
        return repository.findByUser_Id(id);
    }
}

