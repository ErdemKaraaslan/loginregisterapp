package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.controller.dto.FollowDto;
import com.volantx.registrationlogin.entity.Notification;
import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.enums.NotificationType;
import com.volantx.registrationlogin.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final UserService userService;


    public List<Notification> getAllNotifications() {

        return repository.findAll();

    }

    public List<Notification> getAllNotificationsOfUser(String userId) {
        return repository.getNotificationsByReceiverUser_Id(userId);
    }

    public Notification getOneNotificationById(String id) {
        return repository.findById(id).get();
    }

    @Transactional()
    public Notification saveNotification(Notification notification) {
        //ToDo: save den user nesnesi boş dönüyor.
        Notification save = repository.saveAndFlush(notification);
        return save;

    }


    public void deleteNotificationById(String id) {
        repository.deleteById(id);
    }

    public void deleteNotificationByUser(User user){
        repository.deleteNotificationByReceiverUser(user);
    }


    public void updateFollowRequestNotificationToInfo(FollowDto followDto) {
        Notification notification = repository.findNotificationByCausingUser_IdAndAndReceiverUser_Id(followDto.getSenderId().toString(),followDto.getReceiverId().toString());
        notification.setNotificationType(NotificationType.INFO);
        notification.setContent(userService.getOneUserById(followDto.getSenderId().toString()).getFirstName() + " " +
                userService.getOneUserById(followDto.getSenderId().toString()).getLastName() + " started following you");

        repository.save(notification);
    }

    public void deleteNotificationByCausingUserIdAndReceiverUserId(String causingUserId, String receiverUserId) {
        repository.deleteNotificationByCausingUser_IdAndReceiverUser_Id(causingUserId,receiverUserId);
    }
}
