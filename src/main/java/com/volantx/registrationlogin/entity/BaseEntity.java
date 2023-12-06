package com.volantx.registrationlogin.entity;

import com.volantx.registrationlogin.service.UserService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    protected static final int ID_LENGTH = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", length = ID_LENGTH)
    protected String id;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    protected LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDateTime lastModifiedDate = LocalDateTime.now();

    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 50)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;

    @PrePersist
    private void prePersist() {
        createdDate = LocalDateTime.now();
        createdBy = UserService.getLoggedInUser();
    }

    @PreUpdate
    private void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
        lastModifiedBy = UserService.getLoggedInUser();
    }

}
