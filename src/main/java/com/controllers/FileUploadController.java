package com.controllers;

import com.storage.StorageService;
import com.util.Common;

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

    @Autowired
    public FileUploadController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/api/fileupload/user/")
    public ResponseEntity userFileUpload(@RequestParam JSONObject request){
        String username = Common.getStringFieldFromRequest(request, "username");

        if (request.containsKey("file")){
            Object fileObject = request.get("file");
            storageService.store((MultipartFile)fileObject);
            return ResponseEntity.ok().body("Success save :" + fileObject.toString());
        } else {
            return ResponseEntity.badRequest().body("Not found the file");
        }

    }


}
