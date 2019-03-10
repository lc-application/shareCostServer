package com.userprofile;// Created by xuanyuli on 3/9/19.

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

  private final
  UserProfileRepository userProfileRepository;

  @Autowired
  public UserProfileController(UserProfileRepository userProfileRepository) {
    this.userProfileRepository = userProfileRepository;
  }

  @PostMapping("/api/user/usercreate")
  public ResponseEntity userCreate(@RequestBody UserProfile userProfile){
    JSONObject jsonObject = new JSONObject();

    if (userProfileRepository.existsByUsername(userProfile.getUsername())){
      jsonObject.put("status", "username already exists");
      return ResponseEntity.badRequest().body(jsonObject);
    }

    if (userProfileRepository.existsByEmail(userProfile.getEmail())){
      jsonObject.put("status", "email already exists");
      return ResponseEntity.badRequest().body(jsonObject);
    }

    if (userProfileRepository.existsByPhoneNumber(userProfile.getPhoneNumber())){
      jsonObject.put("status", "phoneNumber already exists");
      return ResponseEntity.badRequest().body(jsonObject);
    }

    userProfileRepository.save(userProfile);
    userProfileRepository.flush();

    return ResponseEntity.ok().body(userProfile);
  }

  @PostMapping("/api/user/userfind")
  public ResponseEntity userFind(@RequestBody UserProfile userProfile){
    JSONObject jsonObject = new JSONObject();
      UserProfile newUserProfile = userProfileRepository.findUserProfileByUsername(userProfile.getUsername());
      if (newUserProfile == null){
        jsonObject.put("status", "Not found");
        return ResponseEntity.badRequest().body(jsonObject);
      }

      return ResponseEntity.ok().body(userProfile);
  }

  @PostMapping("/api/user/userupdate")
  public ResponseEntity userUpdate(@RequestBody UserProfile userProfile){
    userProfileRepository.save(userProfile);
    return ResponseEntity.ok(userProfile);
  }

}
