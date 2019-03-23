package com.controllers;

import com.service.FriendService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {
    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService){
        this.friendService = friendService;
    }

    @PostMapping("/api/friend/request")
    public ResponseEntity friendRequest(@RequestBody JSONObject request){
        try{
            String[] result = parseFromAndToFromRequest(request);
            friendService.requestFriend(result[0], result[1]);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Null Request");
        }
    }

    @PostMapping("/api/friend/confirm")
    public ResponseEntity friendConfirm(@RequestBody JSONObject request){
        try{
            String[] result = parseFromAndToFromRequest(request);
            friendService.confirmFriend(result[0], result[1]);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Null Request");
        }
    }

    @PostMapping("/api/friend/delete")
    public ResponseEntity friendDelete(@RequestBody JSONObject request){
        String[] result = parseFromAndToFromRequest(request);
        friendService.deleteFriend(result[0], result[1]);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/friend/get")
    public ResponseEntity friendGet(@RequestBody JSONObject request){
        if (request == null) {
            return ResponseEntity.badRequest().body("Null Request");
        }
        Object fromObject = request.get("from");
        if (fromObject == null) {
            return ResponseEntity.badRequest().body("Null Request");
        }
        String from = fromObject.toString();
        List<String> friendNameList = friendService.getFriendList(from);
        JSONObject jsonObject = new JSONObject();
        for(int i = 0; i < friendNameList.size(); i++) {
            jsonObject.put(i, friendNameList.get(i));
        }
        return ResponseEntity.ok().body(jsonObject);

    }

    private String[] parseFromAndToFromRequest(JSONObject request){
        String[] result = new String[2];
        if (request == null) {
            throw new IllegalArgumentException();
        }

        Object fromObject = request.get("from");
        Object toObject = request.get("to");

        if (fromObject == null || toObject == null) {
            throw new IllegalArgumentException();
        }

        result[0] = fromObject.toString();
        result[1] = toObject.toString();
        return result;
    }
}
