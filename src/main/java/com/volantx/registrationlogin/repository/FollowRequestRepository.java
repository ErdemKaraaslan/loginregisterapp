package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.FollowRequest;
import com.volantx.registrationlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRequestRepository extends JpaRepository<FollowRequest, String> {

    FollowRequest findBySenderIdAndReceiverId(User sender, User receiver);

    void deleteBySenderIdAndReceiverId(User sender, User receiver);
}
