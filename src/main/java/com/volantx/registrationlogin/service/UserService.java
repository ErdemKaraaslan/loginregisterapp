package com.volantx.registrationlogin.service;

import com.volantx.registrationlogin.entity.Role;
import com.volantx.registrationlogin.entity.User;
import com.volantx.registrationlogin.mapper.UserMapper;
import com.volantx.registrationlogin.repository.RoleRepository;
import com.volantx.registrationlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;


    public void saveUser(User user) {

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
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


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}
