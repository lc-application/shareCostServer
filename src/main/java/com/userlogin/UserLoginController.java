package com.userlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class UserLoginController {

    @Autowired
    UserLoginRepository userLoginRepository;

    @PostMapping("/api/user/userlogin")
    public HttpStatus userLogin(@RequestBody UserLogin userLogin){
        List<UserLogin> userLoginList = userLoginRepository.findByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        if (userLoginList.size() == 1){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
