package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.Message;
import com.volantx.registrationlogin.entity.SentMessage;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SentMessageRepository extends JpaRepository<SentMessage, Long> {



    List<SentMessage> findByUser_Id(Long id);

    //ToDo: başka yolla yapılabilir mi? (musa abiye sorulacak)
    @Query(value = "SELECT m.id, m.content, m.create_time FROM SENT_MESSAGES s \n" +
            "            INNER JOIN  MESSAGES m ON s.message_id = m.id\n" +
            "            INNER JOIN  RECEIVED_MESSAGES r ON m.id = r.message_id\n" +
            "            WHERE s.user_id = :senderId and r.user_id = :receiverId"
            , nativeQuery = true)
    List<Tuple> getSpecificMessages(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
