package com.piml.gandalf.gandalf.service;

import com.piml.gandalf.gandalf.dto.SignInDTO;
import com.piml.gandalf.gandalf.dto.SignInResponseDTO;
import com.piml.gandalf.gandalf.entity.User;
import com.piml.gandalf.gandalf.exception.handler.SignInNotAuthorizedException;
import com.piml.gandalf.gandalf.repository.UserRepository;
import com.piml.gandalf.gandalf.utils.ConvertPassword;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    private final ConvertPassword convertPassword = new ConvertPassword();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) throws RuntimeException {
        Optional<User> checkUser = userRepository.findTopByCpfOrUsernameOrEmail(user.getCpf(), user.getUsername(), user.getEmail());
        if (checkUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        try {
            user.setPassword(convertPassword.convertPass(user.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userRepository.save(user);
    }

    public User getById(Long id) throws RuntimeException {
        return userRepository.getById(id);
    }

    public SignInResponseDTO signIn(SignInDTO dto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String convertedPassword = convertPassword.convertPass(dto.getPassword());
        Optional<User> checkUser = userRepository.findByUsernameAndPassword(dto.getUsername(),convertedPassword);
        if(checkUser.isEmpty()) {
            throw new SignInNotAuthorizedException("User not found");
        }
        return new SignInResponseDTO();
    }
}
