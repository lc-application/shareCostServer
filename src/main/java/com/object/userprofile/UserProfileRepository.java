package com.object.userprofile;// Created by xuanyuli on 3/9/19.

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
  boolean existsByEmail(String email);
  boolean existsByPhoneNumber(String phoneNumber);
  boolean existsByUsername(String username);
  boolean existsByUsernameAndPassword(String username, String password);
  UserProfile findUserProfileByUsername(String username);
  UserProfile findUserProfileById(String id);

  List<UserProfile> findAllUserProfileByUsernameIsLike(String username);

  void deleteById(String id);


}
