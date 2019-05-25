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

    public boolean existRelation(String from, String to) {
        return friendRepository.existsByFromAndTo(from, to) ||
                friendRepository.existsByFromAndTo(to, from);
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
        if (!existRelation(from, to)){
            friendRepository.save(new Friend(from, to));
            friendRepository.flush();
        }
    }

    public void deleteFriend(String from, String to){
        if (friendRepository.existsByFromAndTo(from, to)){
            friendRepository.deleteAllByFromAndTo(from, to);
        }
        if (friendRepository.existsByFromAndTo(to, from)){
            friendRepository.deleteAllByFromAndTo(to, from);
        }

    }

    public List<String> getFriendList(String from){
        List<String> result = new ArrayList<>();
        friendRepository.findAllByFromAndStatus(from, 1).forEach(f -> {result.add(f.getTo());});
        friendRepository.findAllByToAndStatus(from, 1).forEach(f -> {result.add(f.getFrom());});
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
