package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.FollowRequestDto;
import com.volantx.registrationlogin.controller.mapper.FollowMapper;
import com.volantx.registrationlogin.controller.mapper.FollowRequestMapper;
import com.volantx.registrationlogin.controller.resource.FollowRequestResource;
import com.volantx.registrationlogin.controller.resource.FollowResource;
import com.volantx.registrationlogin.enums.FollowRequestStatus;
import com.volantx.registrationlogin.service.FollowRequestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public List<FollowRequestResource> getAllFollowRequests() {
        return mapper.modelsToResources(service.getAllFollowRequests());
    }

    @GetMapping("/{id}")
    public FollowRequestResource getOneFollowRequestById(@PathVariable String id) {
        return mapper.modelToResource(service.getOneFollowRequestById(id));
    }

    @GetMapping("/{senderId}/{receiverId}")
    public FollowRequestResource findFollowRequestBySenderIdAndReceiverId(@PathVariable(name = "senderId") String senderId,
                                                                          @PathVariable(name = "receiverId") String receiverId) {
        return mapper.modelToResource(service.findFollowRequestBySenderIdAndReceiverId(senderId, receiverId));
    }

    @PostMapping()
    public FollowRequestResource sendFollowRequest(@RequestBody FollowRequestDto followRequestDto) throws Exception {
        return mapper.modelToResource(service.sendFollowRequest(followRequestDto));
    }

    @PostMapping("/accept/{senderId}/{receiverId}")
    public void acceptFollowRequest(@PathVariable String senderId, @PathVariable String receiverId) {
        service.acceptFollowRequest(senderId, receiverId);
    }

    @Transactional
    @DeleteMapping("/delete/{senderId}/{receiverId}")
    public void deleteFollowRequestBySenderIdAndReceiverId(@PathVariable String senderId, @PathVariable String receiverId) {
        service.deleteFollowRequestBySenderIdAndReceiverId(senderId, receiverId);
    }


}
