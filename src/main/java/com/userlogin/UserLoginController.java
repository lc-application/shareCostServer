package com.userlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserLoginController {

    @Autowired
    UserLoginRepository userLoginRepository;

    @PostMapping("/api/user/userlogin")
    public boolean userLogin(@RequestBody UserLogin userLogin){
        List<UserLogin> userLoginList = userLoginRepository.findByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        return userLoginList.size() == 1;
    }
}
