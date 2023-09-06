package com.volantx.registrationlogin.controller;

import com.volantx.registrationlogin.controller.dto.LoginDto;
import com.volantx.registrationlogin.controller.dto.UserDto;
import com.volantx.registrationlogin.controller.mapper.FollowMapper;
import com.volantx.registrationlogin.controller.mapper.UserMapper;
import com.volantx.registrationlogin.controller.resource.FollowResource;
import com.volantx.registrationlogin.controller.resource.UserResource;
import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;
    private final FollowMapper followMapper;


    // handler method to handle home page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("dto") LoginDto dto) throws Exception {

        userService.findUserByEmailAndPassword(dto.getEmail(), dto.getPassword());
        return "redirect:/users";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        //user.setFirstName("Mustafa");
        //user.setEmail("mustafa@gmail.com");
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(mapper.dtoToModel(userDto));
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    /*@GetMapping("/users")
    public String users(Model model){
        List<UserResource> users = userMapper.modelsToDtos((userService.findAllUsers()));
        model.addAttribute("users", users);
        return "users";
    }*/

    @GetMapping()
    public List<UserResource> getAllUsers() {
        return mapper.modelsToResources(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public UserResource getOneUserById(@PathVariable Long id) {
        return mapper.modelToResource(userService.getOneUserById(id));
    }

    @PostMapping()
    public UserResource saveUser(@RequestBody UserDto userDto) {

        return mapper.modelToResource(userService.saveUser(mapper.dtoToModel(userDto)));
    }

    //alttaki metoda bakacaksın, update etmiyor yeni kayıt ekliyor.
    @PutMapping("/update")
    public UserResource updateUser(@RequestBody UserDto userDto) {

        return mapper.modelToResource(userService.saveUser(mapper.dtoToModel(userDto)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/followings/{id}")
    public List<FollowResource> getFollowings(@PathVariable(name = "id") Long id) {
        return followMapper.modelsToResources(userService.getFollowings(id));
    }

    @GetMapping("/followingsV2/{id}")
    public List<UserResource> getFollowingsV2(@PathVariable(name = "id") Long id) {
        return mapper.modelsToResources(userService.getFollowingsV2(id));
    }

    @GetMapping("/followers/{id}")
    public List<FollowResource> getFollowers(@PathVariable(name = "id") Long id) {
        return followMapper.modelsToResources(userService.getFollowers(id));
    }

    @GetMapping("/followersV2/{id}")
    public List<UserResource> getFollowersV2(@PathVariable(name = "id") Long id) {
        return mapper.modelsToResources(userService.getFollowersV2(id));
    }

}
