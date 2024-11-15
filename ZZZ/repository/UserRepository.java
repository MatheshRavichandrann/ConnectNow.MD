package com.mugiwara.webchatapp.repository;
import com.mugiwara.webchatapp.user.Status;
import com.mugiwara.webchatapp.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByStatus(Status status);
}
