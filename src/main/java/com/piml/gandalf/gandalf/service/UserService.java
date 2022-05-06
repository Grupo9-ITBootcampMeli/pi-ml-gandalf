package com.piml.gandalf.gandalf.service;

import com.piml.gandalf.gandalf.dto.SignInDTO;
import com.piml.gandalf.gandalf.dto.SignInResponseDTO;
import com.piml.gandalf.gandalf.entity.User;
import com.piml.gandalf.gandalf.exception.handler.SignInNotAuthorizedException;
import com.piml.gandalf.gandalf.exception.handler.UserAlreadyExistsException;
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

    /**Method to create an user
     * @param user receive the user data and checks if the user already exists.
     * @return a created user.
     * @throws RuntimeException throws an error if the user already exists;
     */
    public User create(User user) throws RuntimeException {
        Optional<User> checkUser = userRepository.findTopByCpfOrUsernameOrEmail(user.getCpf(), user.getUsername(), user.getEmail());
        if (checkUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        try {
            user.setPassword(convertPassword.convertPass(user.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userRepository.save(user);
    }

    /**Method that get an user by id
     * @param id receives the user id to be found
     * @return the user found.
     * @throws RuntimeException throws an error in case that user isn't found.
     */
    public User getById(Long id) throws RuntimeException {
        return userRepository.getById(id);
    }

    /**Method to make a login of an user.
     * The method first coverts the user password into a hash and then checks in the DB if matches .
     * @param dto receives the username and password.
     * @return the token message (not implemented) simulating one jwt authentication.
     * @throws UnsupportedEncodingException encryption exception
     * @throws NoSuchAlgorithmException encryption exception
     */
    public SignInResponseDTO signIn(SignInDTO dto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String convertedPassword = convertPassword.convertPass(dto.getPassword());
        Optional<User> checkUser = userRepository.findByUsernameAndPassword(dto.getUsername(),convertedPassword);
        if(checkUser.isEmpty()) {
            throw new SignInNotAuthorizedException("User not found");
        }
        return new SignInResponseDTO();
    }
}
