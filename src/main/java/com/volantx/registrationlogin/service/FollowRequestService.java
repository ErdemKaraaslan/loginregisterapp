package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.FollowRequest;
import com.volantx.registrationlogin.enums.FollowRequestStatus;
import com.volantx.registrationlogin.repository.FollowRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowRequestService {

    private final FollowRequestRepository repository;
    private final UserService userService;
    private final FollowService followService;

    public List<FollowRequest> getAllFollowRequests() {
        return repository.findAll();
    }

    public FollowRequest getOneFollowRequestById(Long id) {
        return repository.findById(id).get();
    }

    public FollowRequest saveFollowRequest(Long senderId, Long receiverId) throws Exception {

        if (!userService.checkUser(senderId) || !userService.checkUser(receiverId)) {
            throw new Exception("Sender User or Receiver User does not exists");
        } else {
            FollowRequest followRequest = new FollowRequest(userService.getOneUserById(senderId), userService.getOneUserById(receiverId),
                    FollowRequestStatus.PENDING, LocalDateTime.now());
            return repository.save(followRequest);
        }
    }

    public Follow concludeFollowRequest(Long followRequestId, FollowRequestStatus status) {
        FollowRequest followRequest = repository.findById(followRequestId).get();
        followRequest.setRequestStatus(status);

        if (FollowRequestStatus.ACCEPTED.equals(status)) {
            return followService.saveFollow(new Follow(followRequest.getSenderId(), followRequest.getReceiverId(), LocalDateTime.now()));
        } else {
            return new Follow();
        }

    }

}
