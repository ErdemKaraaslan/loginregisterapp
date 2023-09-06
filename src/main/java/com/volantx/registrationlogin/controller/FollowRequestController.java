package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.FollowDto;
import com.volantx.registrationlogin.controller.dto.FollowRequestDto;
import com.volantx.registrationlogin.controller.mapper.FollowMapper;
import com.volantx.registrationlogin.controller.mapper.FollowRequestMapper;
import com.volantx.registrationlogin.controller.resource.FollowRequestResource;
import com.volantx.registrationlogin.controller.resource.FollowResource;
import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.FollowRequest;
import com.volantx.registrationlogin.enums.FollowRequestStatus;
import com.volantx.registrationlogin.service.FollowRequestService;
import com.volantx.registrationlogin.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow-requests")
public class FollowRequestController {

    private final FollowRequestService service;
    private final FollowRequestMapper mapper;
    private final FollowMapper followMapper;


    @GetMapping()
    public List<FollowRequestResource> getAllFollowRequests(){
        return mapper.modelsToResources(service.getAllFollowRequests());
    }

    @GetMapping("/{id}")
    public FollowRequestResource getOneFollowRequestById(@PathVariable Long id) {
        return mapper.modelToResource(service.getOneFollowRequestById(id));
    }

    @PostMapping()
    public FollowRequestResource saveFollowRequest(@RequestBody FollowRequestDto followRequestDto) throws Exception {
        return mapper.modelToResource(service.saveFollowRequest(followRequestDto.getSenderId(), followRequestDto.getReceiverId()));
    }

    @PostMapping("/conclude/{requestId}")
    public FollowResource concludeFollowRequest(@PathVariable(name = "requestId") Long requestId,
                                      @RequestParam(name = "status") FollowRequestStatus status) {
        return followMapper.modelToResource(service.concludeFollowRequest(requestId,status));
    }




}
