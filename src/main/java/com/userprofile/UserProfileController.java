package com.userprofile;// Created by xuanyuli on 3/9/19.

import com.userlogin.UserLogin;
import com.userlogin.UserLoginRepository;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

  private final UserProfileRepository userProfileRepository;

  private final UserLoginRepository userLoginRepository;

  @Autowired
  public UserProfileController(UserProfileRepository userProfileRepository, UserLoginRepository userLoginRepository) {
    this.userProfileRepository = userProfileRepository;
    this.userLoginRepository = userLoginRepository;
  }

  @PostMapping("/api/user/usercreate")
  public ResponseEntity userCreate(@RequestBody JSONObject request) {
    JSONObject jsonObject = new JSONObject();
    UserProfile userProfile = new UserProfile();

    userProfile.setFirstName((String) request.get("firstName"));
    userProfile.setLastName((String) request.get("lastName"));
    userProfile.setShowName((Boolean) request.get("showName"));
    userProfile.setEmail((String) request.get("email"));
    userProfile.setShowEmail((Boolean) request.get("showEmail"));
    userProfile.setPhoneNumber((String) request.get("phoneNumber"));
    userProfile.setShowPhoneNumber((Boolean) request.get("showPhoneNumber"));
    userProfile.setUsername((String) request.get("username"));


    if (userLoginRepository.existsByUsername(userProfile.getUsername())) {
      jsonObject.put("status", "username already exists");
      return ResponseEntity.badRequest().body(jsonObject);
    }

    if (userProfileRepository.existsByEmail(userProfile.getEmail())) {
      jsonObject.put("status", "email already exists");
      return ResponseEntity.badRequest().body(jsonObject);
    }

    if (userProfileRepository.existsByPhoneNumber(userProfile.getPhoneNumber())) {
      jsonObject.put("status", "phoneNumber already exists");
      return ResponseEntity.badRequest().body(jsonObject);
    }

    userProfileRepository.save(userProfile);
    userProfileRepository.flush();
    UserLogin userLogin = new UserLogin(userProfile.getUsername(), (String) request.get("password"));
    userLoginRepository.save(userLogin);
    userLoginRepository.flush();

    return ResponseEntity.ok().body(userProfile);
  }

  @PostMapping("/api/user/userfind")
  public ResponseEntity userFind(@RequestBody UserProfile userProfile) {
    JSONObject jsonObject = new JSONObject();
    UserProfile newUserProfile = userProfileRepository.findUserProfileByUsername(userProfile.getUsername());
    if (newUserProfile == null) {
      jsonObject.put("status", "Not found");
      return ResponseEntity.badRequest().body(jsonObject);
    }

    return ResponseEntity.ok().body(userProfile);
  }

  @PostMapping("/api/user/userupdate")
  public ResponseEntity userUpdate(@RequestBody UserProfile userProfile) {
    userProfileRepository.save(userProfile);
    return ResponseEntity.ok(userProfile);
  }

}
