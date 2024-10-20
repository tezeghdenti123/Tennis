package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.PublicUserDTO;
import com.bezkoder.springjwt.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<PublicUserDTO> getUsers(){
        return userService.getUsers();
    }
}
