package com.piml.gandalf.gandalf.controller;

import com.piml.gandalf.gandalf.dto.SignInDTO;
import com.piml.gandalf.gandalf.dto.SignInResponseDTO;
import com.piml.gandalf.gandalf.dto.UserDTO;
import com.piml.gandalf.gandalf.entity.User;
import com.piml.gandalf.gandalf.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Api(value = "User")
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    /**POST Route to create a user
     * @param dto receives information on body necessary to create an user
     *            name, cpf, username, password, email, role and in case of
     *            the user be a warehouse agent need to pass the warehouse id.
     * @return return the created user without the password for security reasons.
     */
    @ApiOperation(value = "Register a new User")
    @PostMapping("/user/v1")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto) {
        User user = dto.map();
        UserDTO createdUser = UserDTO.map(userService.create(user));
        return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
    }

    /**GET Route to get a user by id
     * @param id receives an user id
     * @return returns the user if he exists
     */
    @ApiOperation(value = "Find User by ID")
    @GetMapping("/user/v1/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        User user = userService.getById(id);
        UserDTO convertedProduct = UserDTO.map(user);
        return ResponseEntity.ok(convertedProduct);
    }

    /**POST Route Login method
     * @param dto receives username and password
     * @return return a phrase simulating the token (not implemented) in case of success.
     * @throws UnsupportedEncodingException encryption exception .
     * @throws NoSuchAlgorithmException encryption exception .
     */
    @ApiOperation(value = "Performs a Sign In")
    @PostMapping("/user/v1/signin")
    public SignInResponseDTO signIn(@Valid @RequestBody SignInDTO dto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userService.signIn(dto);
    }
}
