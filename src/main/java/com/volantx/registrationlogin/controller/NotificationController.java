package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.NotificationDto;
import com.volantx.registrationlogin.controller.mapper.NotificationMapper;
import com.volantx.registrationlogin.controller.resource.NotificationResource;
import com.volantx.registrationlogin.entity.Notification;
import com.volantx.registrationlogin.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;
    private final NotificationMapper mapper;


    @GetMapping()
    public List<NotificationResource> getAllNotifications() {
        return mapper.modelsToResources(service.getAllNotifications());
    }

    @GetMapping("/{id}")
    public NotificationResource getOneNotificationById(@PathVariable Long id) {
        return mapper.modelToResource(service.getOneNotificationById(id));
    }

    @PostMapping()
    public NotificationResource saveNotification(@RequestBody NotificationDto notificationDto) {
        Notification notification = mapper.dtoToModel(notificationDto);
        return mapper.modelToResource(service.saveNotification(notification));
    }


    /*@PutMapping("/update")
    public NotificationResource updateNotification(@RequestBody NotificationDto notificationDto) {
        return mapper.modelToResource(service.updateNotification(notificationDto));
    }*/


    //@Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteNotificationById(@PathVariable Long id) {
        service.deleteNotificationById(id);
    }

}
