package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.FollowDto;
import com.volantx.registrationlogin.controller.mapper.FollowMapper;
import com.volantx.registrationlogin.controller.resource.FollowResource;
import com.volantx.registrationlogin.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {

    private final FollowService service;
    private final FollowMapper mapper;


    @GetMapping()
    public List<FollowResource> getAllFollows() {
        return mapper.modelsToResources(service.getAllFollows());
    }

    @GetMapping("/{id}")
    public FollowResource getOneFollowById(@PathVariable Long id) {
        return mapper.modelToResource(service.getOneFollowById(id));
    }

    @PostMapping()
    public FollowResource saveFollow(@RequestBody FollowDto followDto) throws Exception {
        return mapper.modelToResource(service.saveFollow(followDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFollow(@PathVariable("id") Long followId) {
        service.deleteFollow(followId);
    }

}
