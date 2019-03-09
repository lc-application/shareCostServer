package com.userlogin;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserLoginController {

    @Autowired
    UserLoginRepository userLoginRepository;

    @PostMapping("/api/user/userlogin")
    public ResponseEntity<JSONObject> userLogin(@RequestBody UserLogin userLogin){
        List<UserLogin> userLoginList = userLoginRepository.findByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        JSONObject jsonObject = new JSONObject();
        if (userLoginList.size() == 1){
            jsonObject.put("status", "Success");
            return ResponseEntity.ok(jsonObject);
        } else {
            jsonObject.put("status", "Failed");
            return ResponseEntity.ok(jsonObject);
        }
    }
}
