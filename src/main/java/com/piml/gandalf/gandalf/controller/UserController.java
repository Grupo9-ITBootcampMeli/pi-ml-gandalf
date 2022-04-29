package com.piml.gandalf.gandalf.controller;

import com.piml.gandalf.gandalf.dto.UserDTO;
import com.piml.gandalf.gandalf.entity.User;
import com.piml.gandalf.gandalf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/v1")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto) {
        User user = dto.map();
        UserDTO createdUser = UserDTO.map(userService.create(user));
        return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
    }
    @GetMapping("/user/v1/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        User user = userService.getById(id);
        UserDTO convertedProduct = UserDTO.map(user);
        return ResponseEntity.ok(convertedProduct);
    }
}
