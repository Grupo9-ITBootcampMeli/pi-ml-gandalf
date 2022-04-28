package com.piml.gandalf.gandalf.service;

import com.piml.gandalf.gandalf.dto.UserDTO;
import com.piml.gandalf.gandalf.entity.User;
import com.piml.gandalf.gandalf.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) throws RuntimeException {
        Optional<User> checkUser = userRepository.findTopByCpfOrUsernameOrEmail(user.getCpf(), user.getUsername(), user.getEmail());
        if (checkUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return userRepository.save(user);
    }
    public User getById(Long id) throws RuntimeException {
        return userRepository.getById(id);
    }
}
