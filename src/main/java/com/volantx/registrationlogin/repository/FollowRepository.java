package com.volantx.registrationlogin.repository;

import com.volantx.registrationlogin.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, String> {

    void deleteFollowByFollower_IdAndFollowing_Id(String followerId, String followingId);

    List<Follow> findFollowsByFollowing_Id(String id);

    List<Follow> findFollowsByFollower_Id(String id);

}
