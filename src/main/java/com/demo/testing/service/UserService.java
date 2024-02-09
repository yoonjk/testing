package com.demo.testing.service;

import com.demo.testing.domain.User;
import com.demo.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> getUserAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
