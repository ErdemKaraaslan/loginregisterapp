package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.controller.dto.FollowRequestDto;
import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.FollowRequest;
import com.volantx.registrationlogin.entity.Notification;
import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.enums.FollowRequestStatus;
import com.volantx.registrationlogin.enums.NotificationType;
import com.volantx.registrationlogin.repository.FollowRequestRepository;
import com.volantx.registrationlogin.repository.NotificationRepository;
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
    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    public List<FollowRequest> getAllFollowRequests() {
        return repository.findAll();
    }

    public FollowRequest getOneFollowRequestById(String id) {
        return repository.findById(id).get();
    }

    public FollowRequest findFollowRequestBySenderIdAndReceiverId(String senderId, String receiverId) {
        FollowRequest followRequest = repository.findBySenderIdAndReceiverId(userService.getOneUserById(senderId), userService.getOneUserById(receiverId));
        //System.out.println("follow request: " + followRequest);
        if (followRequest == null) {
            return new FollowRequest();
        } else {
            return followRequest;
        }
    }

    public FollowRequest sendFollowRequest(FollowRequestDto followRequestDto) throws Exception {

        if (!userService.checkUser(followRequestDto.getSenderId()) || !userService.checkUser(followRequestDto.getReceiverId())) {
            throw new Exception("Sender User or Receiver User does not exists");
        } else {
            User user = userService.getOneUserById(followRequestDto.getSenderId().toString());

            FollowRequest followRequest = new FollowRequest();

            followRequest.setSenderId(user);
            followRequest.setReceiverId(userService.getOneUserById(followRequestDto.getReceiverId().toString()));
            followRequest.setRequestStatus(FollowRequestStatus.PENDING);
            followRequest.setRequestTime(LocalDateTime.now());


            Notification notification = new Notification();

            notification.setNotificationType(NotificationType.FOLLOW_REQUEST);
            notification.setReceiverUser(userService.getOneUserById(followRequestDto.getReceiverId().toString()));
            notification.setCausingUser(userService.getOneUserById(followRequestDto.getSenderId().toString()));
            notification.setContent(userService.getOneUserById(followRequestDto.getSenderId().toString()).getFirstName() + " "
                    + userService.getOneUserById(followRequestDto.getSenderId().toString()).getLastName() + " wants to follow you");
            notification.setSentTime(LocalDateTime.now());


            notificationService.saveNotification(notification);

            return repository.save(followRequest);
        }
    }

    public Follow concludeFollowRequest(String followRequestId, FollowRequestStatus status) {
        FollowRequest followRequest = repository.findById(followRequestId).get();
        followRequest.setRequestStatus(status);

        if (FollowRequestStatus.ACCEPTED.equals(status)) {
            Follow follow = new Follow();
            follow.setFollower(followRequest.getSenderId());
            follow.setFollower(followRequest.getReceiverId());
            follow.setFollowTime(LocalDateTime.now());
            return followService.saveFollow(follow);
        } else {
            return new Follow();
        }

    }

    public void deleteFollowRequestBySenderIdAndReceiverId(String senderId, String receiverId) {
        notificationService.deleteNotificationByUser(userService.getOneUserById(receiverId));
        repository.deleteBySenderIdAndReceiverId(userService.getOneUserById(senderId), userService.getOneUserById(receiverId));
    }


    public void acceptFollowRequest(String senderId, String receiverId) {
        FollowRequest followRequest = repository.findBySenderIdAndReceiverId(userService.getOneUserById(senderId), userService.getOneUserById(receiverId));
        followRequest.setRequestStatus(FollowRequestStatus.ACCEPTED);
        repository.save(followRequest);
    }
}
