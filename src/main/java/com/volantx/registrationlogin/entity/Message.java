package com.volantx.registrationlogin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable=false)
    private String content;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    public Message(String content, LocalDateTime createTime) {
        this.content = content;
        this.createTime = createTime;
    }
}
