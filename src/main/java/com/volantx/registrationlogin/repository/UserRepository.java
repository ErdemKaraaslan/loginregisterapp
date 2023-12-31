package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);



}
