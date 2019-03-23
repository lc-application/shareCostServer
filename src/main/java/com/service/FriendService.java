package com.service;

import com.object.friend.Friend;
import com.object.friend.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {
    private final FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository){
        this.friendRepository = friendRepository;
    }

    public void confirmFriend(String from, String to){
        Friend friend = friendRepository.findByFromAndTo(from, to);
        if (friend == null){
            friend = friendRepository.findByFromAndTo(to, from);
        }
        friend.setStatus(1);
        friendRepository.save(friend);
    }

    public void requestFriend(String from, String to){
        friendRepository.save(new Friend(from, to));
        friendRepository.flush();
    }

    public void deleteFriend(String from, String to){
        if (friendRepository.existsByFromAndTo(from, to)){
            friendRepository.deleteByFromAndTo(from, to);
        } else {
            friendRepository.deleteByFromAndTo(to, from);
        }

    }

    public List<String> getFriendList(String from){
        List<Friend> friendList = friendRepository.findAllByFromAndStatus(from, 1);
        friendList.addAll(friendRepository.findAllByToAndStatus(from, 1));
        List<String> result = new ArrayList<>();
        for(Friend f : friendList){
            result.add(f.getTo());
        }
        return result;
    }

    public List<String> getRequestList(String from){
        List<Friend> friendList = friendRepository.findAllByToAndStatus(from, 0);
        List<String> result = new ArrayList<>();
        for(Friend f : friendList){
            result.add(f.getTo());
        }
        return result;
    }
}
