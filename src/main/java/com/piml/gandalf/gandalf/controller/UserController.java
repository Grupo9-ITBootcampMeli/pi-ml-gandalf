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

    @ApiOperation(value = "Register a new User")
    @PostMapping("/user/v1")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto) {
        User user = dto.map();
        UserDTO createdUser = UserDTO.map(userService.create(user));
        return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Find User by ID")
    @GetMapping("/user/v1/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        User user = userService.getById(id);
        UserDTO convertedProduct = UserDTO.map(user);
        return ResponseEntity.ok(convertedProduct);
    }
    @ApiOperation(value = "Performs a Sign In")
    @PostMapping("/user/v1/signin")
    public SignInResponseDTO signIn(@Valid @RequestBody SignInDTO dto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userService.signIn(dto);
    }
}
