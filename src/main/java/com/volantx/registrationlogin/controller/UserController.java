package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.LoginDto;
import com.volantx.registrationlogin.controller.dto.UserDto;
import com.volantx.registrationlogin.controller.mapper.FollowMapper;
import com.volantx.registrationlogin.controller.mapper.UserMapper;
import com.volantx.registrationlogin.controller.resource.FollowResource;
import com.volantx.registrationlogin.controller.resource.UserResource;
import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;
    private final FollowMapper followMapper;

    @PostMapping("/login")
    public UserResource login(@RequestBody LoginDto dto) throws Exception {

        User user = userService.findUserByEmailAndPassword(dto.getEmail(), dto.getPassword());
        return mapper.modelToResource(user);
    }

    @PostMapping("/register")
    public UserResource register(@RequestBody UserDto userDto) throws Exception {
        return mapper.modelToResource(userService.register(mapper.dtoToModel(userDto)));
    }

    @GetMapping()
    public List<UserResource> getAllUsers() {
        return mapper.modelsToResources(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public UserResource getOneUserById(@PathVariable String id) {
        return mapper.modelToResource(userService.getOneUserById(id));
    }

    @GetMapping("/email/{email}")
    public UserResource getOneUserByEmail(@PathVariable String email) {
        return mapper.modelToResource(userService.findUserByEmail(email));
    }


    //alttaki metoda bakacaksın, update etmiyor yeni kayıt ekliyor.
    @PutMapping("/update")
    public UserResource updateUser(@RequestBody UserDto userDto) {

        return mapper.modelToResource(userService.editUser(mapper.dtoToModel(userDto)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/followings/{id}")
    public List<FollowResource> getFollowings(@PathVariable(name = "id") String id) {
        return followMapper.modelsToResources(userService.getFollowings(id));
    }

    @GetMapping("/followingsV2/{id}")
    public List<UserResource> getFollowingsV2(@PathVariable(name = "id") String id) {
        return mapper.modelsToResources(userService.getFollowingsV2(id));
    }

    @GetMapping("/followers/{id}")
    public List<FollowResource> getFollowers(@PathVariable(name = "id") String id) {
        return followMapper.modelsToResources(userService.getFollowers(id));
    }

    @GetMapping("/followersV2/{id}")
    public List<UserResource> getFollowersV2(@PathVariable(name = "id") String id) {
        return mapper.modelsToResources(userService.getFollowersV2(id));
    }

    @PutMapping("edit")
    public UserResource editUser(@RequestBody UserDto userDto) {
        return mapper.modelToResource(userService.editUser(mapper.dtoToModel(userDto)));
    }


}
