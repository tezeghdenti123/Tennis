package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.DTO.PublicCoachDTO;
import com.bezkoder.springjwt.mappers.PublicCoachMapper;
import com.bezkoder.springjwt.models.Coach;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.CoachRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    public ResponseEntity<?> saveCoach(PublicCoachDTO publicCoachDTO) {
        if (userRepository.existsByUsername(publicCoachDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(publicCoachDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Coach coach= PublicCoachMapper.INSTANCE.toEntity(publicCoachDTO);
        Set<Role> roles=new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_COACH)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        coach.setPassword(encoder.encode(coach.getPassword()));
        coach.setRoles(roles);
        coachRepository.save(coach);
        return ResponseEntity.ok("Saved!");
    }

    public List<Coach> getCoachs() {
        List<Coach> coachList=coachRepository.findAll();

        return coachList;
        //return publicCoachDTOList;
    }
}
