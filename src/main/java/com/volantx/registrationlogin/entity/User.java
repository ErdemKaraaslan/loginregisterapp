package com.volantx.registrationlogin.entity;

import com.volantx.registrationlogin.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {


    private static final long serialVersionUID = 1L;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "about")
    private String about;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles = new ArrayList<>();

    /*@OneToMany(mappedBy = "follower")
    private List<Follow> followers;

    @OneToMany(mappedBy = "following")
    private List<Follow> followings;

    @OneToMany(mappedBy = "senderId")
    private List<FollowRequest> sentFollowRequests;

    @OneToMany(mappedBy = "receiverId")
    private List<FollowRequest> receivedFollowRequests;

    @OneToMany(mappedBy = "receiverUser")
    private List<Notification> notifications;*/
}