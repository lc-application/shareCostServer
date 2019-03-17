package com.controllers;

import com.service.UserService;
import com.userprofile.UserProfile;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/userlogin")
    public ResponseEntity userLogin(@RequestBody JSONObject request){
        String username = userService.getUsernameFromRequest(request);
        Object passwordObject = request.get("password");
        String password = passwordObject.toString();

        if (userService.checkUserLogin(username, password)){
            return ResponseEntity.notFound().build();
        }

        UserProfile userProfile = userService.getFullUserProfile(username);
        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("/api/user/usercreate")
    public ResponseEntity userCreate(@RequestBody JSONObject request) {

        String username = userService.getUsernameFromRequest(request);

        // Password for login
        Object passwordObject = request.get("password");
        if (passwordObject == null) {
            return ResponseEntity.badRequest().build();
        }
        String password = passwordObject.toString();
        userService.createUserLogin(username, password);


        // User profile
        UserProfile userProfile = new UserProfile();
        userService.getUserProfileFromRequest(request, userProfile);
        userService.createUserProfile(userProfile);

        if (userService.checkUserNameExists(username)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("create failed");
        }


    }

    @PostMapping("/api/user/userupdate")
    public ResponseEntity userUpdate(@RequestBody JSONObject request) {
        String username = userService.getUsernameFromRequest(request);

        UserProfile oldUserProfile = userService.getFullUserProfile(username);
        int userId = oldUserProfile.getId();

        // Password for update
        Object passwordObject = request.get("password");
        if (passwordObject != null) {
            String password = passwordObject.toString();
            userService.updateUserLogin(userId, username, password);
        }
        try{
            userService.getUserProfileFromRequest(request, oldUserProfile);
        } catch (IllegalArgumentException e){
            ResponseEntity.badRequest().build();
        }


        userService.updateUserProfile(oldUserProfile);
        return ResponseEntity.ok().build();

    }



    @PostMapping("/api/user/userdelete")
    public ResponseEntity userDelete(@RequestBody JSONObject request){
        String username = userService.getUsernameFromRequest(request);
        userService.deleteUserLogin(username);
        userService.deleteUserProfile(username);
        return ResponseEntity.ok().build();
    }

}


