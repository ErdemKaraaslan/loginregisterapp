package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {

}
