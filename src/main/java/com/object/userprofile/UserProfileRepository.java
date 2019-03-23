package com.object.userprofile;// Created by xuanyuli on 3/9/19.

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
  boolean existsByEmail(String email);
  boolean existsByPhoneNumber(String phoneNumber);
  UserProfile findUserProfileByUsername(String username);
  void deleteByUsername(String username);

}
