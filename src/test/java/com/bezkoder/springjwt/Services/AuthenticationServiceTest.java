package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.repository.AdminRepository;
import com.bezkoder.springjwt.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")  // Use the test profile for H2
@Transactional
class AuthenticationServiceTest {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    AdminRepository adminRepository;
    @Test
    void saveUser() {
        SignupRequest signupRequest=new SignupRequest();
        signupRequest.setEmail("Test@gmail.com");
        signupRequest.setName("Test");
        signupRequest.setUsername("Test123");
        signupRequest.setPhone("94102105");
        signupRequest.setPassword("Test123456");
        Set<String> roles=new HashSet<>();
        String role="client";
        roles.add(role);
        signupRequest.setRole(roles);
        authenticationService.saveUser(signupRequest);
        assertEquals(1,clientRepository.findAll().size());

        signupRequest=new SignupRequest();
        signupRequest.setEmail("Test1@gmail.com");
        signupRequest.setName("Test1");
        signupRequest.setUsername("Test1123");
        signupRequest.setPhone("941021055");
        signupRequest.setPassword("Test1213456");
        roles=new HashSet<>();
        role="admin";
        roles.add(role);
        signupRequest.setRole(roles);
        authenticationService.saveUser(signupRequest);
        assertEquals(1,adminRepository.findAll().size());

    }
}