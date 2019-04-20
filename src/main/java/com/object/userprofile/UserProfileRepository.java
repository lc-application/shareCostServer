package com.object.userprofile;// Created by xuanyuli on 3/9/19.

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
  boolean existsByEmail(String email);
  boolean existsByPhoneNumber(String phoneNumber);
  boolean existsByUsername(String username);
  boolean existsByUsernameAndPassword(String username, String password);
  UserProfile findUserProfileByUsername(String username);
  void deleteByUsername(String username);


}
