package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.entity.Follow;
import com.volantx.registrationlogin.entity.Role;
import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.repository.RoleRepository;
import com.volantx.registrationlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User getLoggedInUser() {
        //ToDo:
        return userRepository.findById(1L).get();
    }

    public User saveUser(User user) {

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User findUserByEmailAndPassword(String email, String password) throws Exception {
        if (email.isEmpty() || password.isEmpty()) {
            throw new Exception("email ve password dolu olmalıdır");
        }

        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("email veya parola hatalı");
        }
    }


    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public boolean checkUser(Long id) {
        return userRepository.existsById(id);
    }


    public List<Follow> getFollowings(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() ? user.get().getFollowers() : new ArrayList<>();
    }

    public List<User> getFollowingsV2(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        List<Follow> followers = user.get().getFollowers();

        List<User> userList = followers.stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());

        return userList;
    }

    public List<Follow> getFollowers(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() ? user.get().getFollowings() : new ArrayList<>();
    }

    public List<User> getFollowersV2(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        List<Follow> followings = user.get().getFollowings();

        List<User> userList = followings.stream()
                .map(Follow::getFollower)
                .collect(Collectors.toList());

        return userList;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }



}
