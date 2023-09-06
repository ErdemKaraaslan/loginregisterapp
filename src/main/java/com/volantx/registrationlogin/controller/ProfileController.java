package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @GetMapping()
    public String home(Model model) {
        Long userId = 1L;
        User user = userService.getOneUserById(userId);
        List<User> followers = userService.getFollowersV2(userId);
        List<User> followings = userService.getFollowingsV2(userId);
        log.info("user: {}", user);
        model.addAttribute("user", user);
        model.addAttribute("followers", followers);
        model.addAttribute("followings", followings);

        return "profile";

    }


}
