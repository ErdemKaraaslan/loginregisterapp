package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.controller.dto.FollowDto;
import com.volantx.registrationlogin.controller.mapper.FollowMapper;
import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private UserService userService;
    private FollowRepository repository;
    private FollowMapper mapper;
    @Autowired
    public FollowService(FollowMapper mapper, FollowRepository repository, @Lazy UserService userService){
        this.userService = userService;
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<Follow> getAllFollows() {
        return  repository.findAll();
    }

    public Follow getOneFollowById(String id) {
        return repository.findById(id).get();
    }

    public List<Follow> getFollowersByUserId(String id){
        return repository.findFollowsByFollowing_Id(id);
    }

    public List<Follow> getFollowingsByUserId(String id){
        return repository.findFollowsByFollower_Id(id);
    }

    public Follow saveFollow(FollowDto followDto) throws Exception {
        if (!userService.checkUser(followDto.getSenderId()) || !userService.checkUser(followDto.getReceiverId())){
            throw new Exception("User with followerId or followingId does not exists");
        }
        else{

            Follow follow = new Follow();
            follow.setFollower(userService.getOneUserById(followDto.getSenderId().toString()));
            follow.setFollowing(userService.getOneUserById(followDto.getReceiverId().toString()));
            follow.setFollowTime(LocalDateTime.now());
            return repository.save(follow);
        }

    }

    public Follow saveFollow(Follow follow){
        return repository.save(follow);
    }

    public void deleteFollow(String followId) {
        repository.deleteById(followId);
    }

    public void deleteFollowByFollowerIdAndFollowingId(String followerId, String followingId) {
        repository.deleteFollowByFollower_IdAndFollowing_Id(followerId,followingId);
    }
}
