package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.repository.AdminRepository;
import com.bezkoder.springjwt.repository.ClientRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private

    @Test
    void registerUser() {
        SignupRequest signupRequest=new SignupRequest();
        signupRequest.setEmail("Test@gmail.com");
        signupRequest.setName("Test");
        signupRequest.setUsername("Test123");
        signupRequest.setPhone("94102105");
        signupRequest.setPassword("Test123456");
        Set<String> roles=new HashSet<>();
        String role="admin";
        roles.add(role);
        signupRequest.setRole(roles);

    }
}