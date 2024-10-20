package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.DTO.PrivateAdminDTO;
import com.bezkoder.springjwt.DTO.PublicAdminDTO;
import com.bezkoder.springjwt.mappers.PrivateAdminMappers;
import com.bezkoder.springjwt.mappers.PublicAdminMapper;
import com.bezkoder.springjwt.models.Admin;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.AdminRepository;
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
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    public List<PublicAdminDTO> getAllAdmins(){
        List<Admin>adminList= adminRepository.findAll();
        List<PublicAdminDTO>publicAdminDTOList=new ArrayList<PublicAdminDTO>();
        for(int i=0;i<adminList.size();i++){
            PublicAdminDTO publicAdminDTO= PublicAdminMapper.INSTANCE.toDTO(adminList.get(i));
            publicAdminDTOList.add(publicAdminDTO);
        }
        return publicAdminDTOList;
    }

    public ResponseEntity<?> updateAdmin(PrivateAdminDTO privateAdminDTO){
        System.out.println(privateAdminDTO.getId());
        if(privateAdminDTO.getId()==null){
            return ResponseEntity.ok("This Id is not found!");
        }
        Admin admin= PrivateAdminMappers.INSTANCE.toEntity(privateAdminDTO);
        if(adminRepository.existsById(admin.getId())){
            System.out.println(adminRepository.findById(admin.getId()).isPresent());
            Admin savedAdmin=adminRepository.findById(admin.getId()).orElseThrow();
            savedAdmin.setRoles(admin.getRoles());
            savedAdmin.setPassword(encoder.encode(admin.getPassword()));
            savedAdmin.setUsername(admin.getUsername());
            savedAdmin.setName(admin.getName());
            savedAdmin.setEmail(admin.getEmail());
            savedAdmin.setPhone(admin.getPhone());
            adminRepository.save(savedAdmin);
            ResponseEntity.ok("Admin updated succesfully!");
        }
        return ResponseEntity.ok("This Id is not found!");
    }


    public ResponseEntity<?> deleteAdmin(Long adminId){
        if((adminId!=null)&&(adminRepository.existsById(adminId))){
            adminRepository.deleteById(adminId);
            return ResponseEntity.ok("Admin deleted succefully!");
        }
        else{
            return ResponseEntity.ok("User not found!");
        }

    }

    public ResponseEntity<?> saveAdmin(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Admin admin=new Admin();
        admin.setName(signupRequest.getName());
        admin.setEmail(signupRequest.getEmail());
        admin.setUsername(signupRequest.getUsername());
        admin.setPhone(signupRequest.getPhone());
        admin.setPassword(encoder.encode(signupRequest.getPassword()));
        Set<Role>roles=new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        admin.setRoles(roles);
        admin=adminRepository.save(admin);
        return ResponseEntity.ok("Admin saved");
    }
}
