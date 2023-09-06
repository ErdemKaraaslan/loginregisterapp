package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.entity.Notification;
import com.volantx.registrationlogin.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;


    public List<Notification> getAllNotifications() {

        return repository.findAll();

    }

    public Notification getOneNotificationById(Long id) {
        return repository.findById(id).get();
    }

    @Transactional()
    public Notification saveNotification(Notification notification) {
        //ToDo: save den user nesnesi boş dönüyor.
        Notification save = repository.saveAndFlush(notification);
        return save;

    }


    public void deleteNotificationById(Long id) {
        repository.deleteById(id);
    }
}
