package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.Notification;
import com.volantx.registrationlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {

    List<Notification> getNotificationsByReceiverUser_Id(String userId);

    void deleteNotificationByReceiverUser(User user);

    Notification findNotificationByCausingUser_IdAndAndReceiverUser_Id(String senderId, String receiverId);

    void deleteNotificationByCausingUser_IdAndReceiverUser_Id(String causingUserId, String receiverUserId);

}
