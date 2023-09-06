package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    

}
