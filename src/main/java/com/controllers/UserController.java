package com.controllers;

import com.object.userprofile.UserProfile;
import com.service.EmailService;
import com.service.UserService;
import com.util.Common;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user/userlogin")
    public ResponseEntity userLogin(@RequestBody JSONObject request){
        String username = Common.getStringFieldFromRequest(request, "username");
        String password = Common.getStringFieldFromRequest(request, "password");

        if (!userService.checkUserNameExists(username)) {
            return ResponseEntity.badRequest().body("Error: Username doest not exists");
        }
        if (!userService.checkUserLogin(username, password)){
            return ResponseEntity.badRequest().body("Error: Authentication Failed");
        }

        UserProfile userProfile = userService.getFullUserProfile(username);
        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("/api/user/usercreate")
    public ResponseEntity userCreate(@RequestBody JSONObject request) {

        String username = Common.getStringFieldFromRequest(request, "username");

        if (userService.checkUserNameExists(username)){
            return ResponseEntity.badRequest().body("Error: Username exists");
        }

        // User profile
        UserProfile userProfile = new UserProfile();
        userService.getUserProfileFromRequest(request, userProfile);

        if (userProfile.getEmail() != null){
            EmailService.sendRegisterEmail(userProfile.getEmail(), userProfile.getUsername());
        }

        userService.createUserProfile(userProfile);

        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("/api/user/userupdate")
    public ResponseEntity userUpdate(@RequestBody JSONObject request) {
        String userId = Common.getStringFieldFromRequest(request, "id");

        UserProfile oldUserProfile = userService.getFullUserProfile(userId);
        try{
            userService.getUserProfileFromRequest(request, oldUserProfile);
        } catch (IllegalArgumentException e){
            ResponseEntity.badRequest().body("Error: Unable to update");
        }

        userService.updateUserProfile(oldUserProfile);
        return ResponseEntity.ok().build();

    }



    @PostMapping("/api/user/userdelete")
    public ResponseEntity userDelete(@RequestBody JSONObject request){
        String userId = Common.getStringFieldFromRequest(request, "id");
        userService.deleteUserProfile(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/user/userpart")
    public ResponseEntity userPart(@RequestBody JSONObject request){
        try{
            String username = Common.getStringFieldFromRequest(request, "username");
            UserProfile userProfile = userService.getPartUserProfile(username);
            return ResponseEntity.ok().body(userProfile);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error: Not found username");
        }

    }

}


