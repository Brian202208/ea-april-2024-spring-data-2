package com.brianmugalu.springdata2.service;

import com.brianmugalu.springdata2.repository.dto.UserRepo;
import com.brianmugalu.springdata2.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
