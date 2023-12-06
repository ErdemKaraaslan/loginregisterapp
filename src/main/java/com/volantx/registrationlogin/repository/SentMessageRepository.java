package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.SentMessage;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SentMessageRepository extends JpaRepository<SentMessage, String> {


    List<SentMessage> findByUser_Id(String id);

    //ToDo: başka yolla yapılabilir mi? (musa abiye sorulacak)
    @Query(value = "SELECT m.id, m.content, m.create_time FROM SENT_MESSAGES s \n" +
            "            INNER JOIN  MESSAGES m ON s.message_id = m.id\n" +
            "            INNER JOIN  RECEIVED_MESSAGES r ON m.id = r.message_id\n" +
            "            WHERE s.user_id = :senderId and r.user_id = :receiverId"
            , nativeQuery = true)
    List<Tuple> getSpecificMessages(@Param("senderId") String senderId, @Param("receiverId") String receiverId);

    @Query(value = """ 
            SELECT m.id, m.content, m.create_time, s.user_id sender, r.user_id receiver
            FROM SENT_MESSAGES s
            INNER JOIN  MESSAGES m ON s.message_id = m.id
            INNER JOIN  RECEIVED_MESSAGES r ON m.id = r.message_id
            WHERE s.user_id = :senderId and r.user_id = :receiverId
            or s.user_id = :receiverId and r.user_id = :senderId
            ORDER BY m.create_time ASC 
               """
            , nativeQuery = true)
    List<Tuple> getAllMessagesBetweenTwoPerson(@Param("senderId") String senderId, @Param("receiverId") String receiverId);


}
