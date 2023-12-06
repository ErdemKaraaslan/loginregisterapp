package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.ReceivedMessage;
import com.volantx.registrationlogin.entity.SentMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceivedMessageRepository extends JpaRepository<ReceivedMessage, String> {


    @Query(value = "SELECT * FROM RECEIVED_MESSAGES WHERE user_id = :id", nativeQuery = true)
    List<ReceivedMessage> getAllReceivedMessagesByUser(Long id);
}
