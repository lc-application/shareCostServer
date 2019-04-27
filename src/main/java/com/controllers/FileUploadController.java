package com.controllers;

import com.service.UserService;
import com.storage.StorageService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    private final UserService userService;

    @Autowired
    public FileUploadController(StorageService storageService, UserService userService){
        this.storageService = storageService;
        this.userService = userService;
    }

    @PostMapping("/api/fileupload/user/")
    public ResponseEntity userFileUpload(@RequestParam JSONObject request){
        String username = userService.getStringFieldFromRequest(request, "username");

        if (request.containsKey("file")){
            Object fileObject = request.get("file");
            storageService.store((MultipartFile)fileObject);
            return ResponseEntity.ok().body("Success save :" + fileObject.toString());
        } else {
            return ResponseEntity.badRequest().body("Not found the file");
        }

    }


}
