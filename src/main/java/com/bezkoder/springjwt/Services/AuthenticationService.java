package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.*;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    JwtUtils jwtUtils;


    public ResponseEntity<?> saveUser(SignupRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            Client client=new Client();
            client.setName(signUpRequest.getName());
            client.setPhone(signUpRequest.getPhone());
            client.setEmail(signUpRequest.getEmail());
            client.setUsername(signUpRequest.getUsername());
            client.setPassword(encoder.encode(signUpRequest.getPassword()));
            client.setRoles(roles);
            clientRepository.save(client);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        // Create new user's account
                        Admin admin=new Admin();
                        admin.setName(signUpRequest.getName());
                        admin.setPhone(signUpRequest.getPhone());
                        admin.setEmail(signUpRequest.getEmail());
                        admin.setUsername(signUpRequest.getUsername());
                        admin.setPassword(encoder.encode(signUpRequest.getPassword()));
                        admin.setRoles(roles);
                        adminRepository.save(admin);
                        break;
                    case "client":
                        Role modRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        Client client=new Client();
                        client.setName(signUpRequest.getName());
                        client.setPhone(signUpRequest.getPhone());
                        client.setEmail(signUpRequest.getEmail());
                        client.setUsername(signUpRequest.getUsername());
                        client.setPassword(encoder.encode(signUpRequest.getPassword()));
                        client.setRoles(roles);
                        clientRepository.save(client);
                        break;
                    case "coach":
                        Role coachRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(coachRole);
                        Coach coach=new Coach();
                        coach.setName(signUpRequest.getName());
                        coach.setPhone(signUpRequest.getPhone());
                        coach.setEmail(signUpRequest.getEmail());
                        coach.setUsername(signUpRequest.getUsername());
                        coach.setPassword(signUpRequest.getPassword());
                        coach.setRoles(roles);
                        coachRepository.save(coach);
                }
            });
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
