package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.controller.dto.FollowDto;
import com.volantx.registrationlogin.controller.mapper.FollowMapper;
import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository repository;
    private final UserService userService;
    private final FollowMapper mapper;

    public List<Follow> getAllFollows() {
        return  repository.findAll();
    }

    public Follow getOneFollowById(Long id) {
        return repository.findById(id).get();
    }

    public Follow saveFollow(FollowDto followDto) throws Exception {
        if (!userService.checkUser(followDto.getFollowerId()) || !userService.checkUser(followDto.getFollowingId())){
            throw new Exception("User with followerId or followingId does not exists");
        }
        else{
            Follow follow = new Follow(userService.getOneUserById(followDto.getFollowerId()),
                    userService.getOneUserById(followDto.getFollowingId()), LocalDateTime.now());
            return repository.save(follow);
        }

    }

    public Follow saveFollow(Follow follow){
        return repository.save(follow);
    }

    public void deleteFollow(Long followId) {
        repository.deleteById(followId);
    }
}
