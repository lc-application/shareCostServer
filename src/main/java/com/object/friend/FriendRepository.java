package com.object.friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
    void deleteByFromAndTo(String from, String to);
    Friend findByFromAndTo(String from, String to);
    boolean existsByFromAndTo(String from, String to);
    List<Friend> findAllByFromAndToAndStatus(String from, String to, int status);
    List<Friend> findAllByFrom(String from, int status);
    List<Friend> findAllByTo(String to, int status);
}
