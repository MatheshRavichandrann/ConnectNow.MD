package com.mugiwara.webchatapp.service;

import com.mugiwara.webchatapp.repository.UserRepository;
import com.mugiwara.webchatapp.user.Status;
import com.mugiwara.webchatapp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository; // Cons DI

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user){
        var storedUser = repository.findById(user.getNickName())
                .orElse(null);
        if (storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
        }
    }


    public List<User> findConnectedUsers(){
        return repository.findByStatus(Status.ONLINE);
    }
}
