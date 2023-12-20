package com.chatingan.service;

import com.chatingan.Repository.UserRepository;
import com.chatingan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;


    public User findByUsername(String username) {
        final Optional<User> findUser = userRepository.findByUsername(username);

        if (findUser.isPresent()) {
            return findUser.get();
        }

        return null;
    }

    public User create(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
}
