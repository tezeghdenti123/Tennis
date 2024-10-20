package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.PrivateAdminDTO;
import com.bezkoder.springjwt.DTO.PublicAdminDTO;
import com.bezkoder.springjwt.Services.AdminService;
import com.bezkoder.springjwt.models.Admin;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admins")
    public List<PublicAdminDTO> getAllAdmin(){
        return adminService.getAllAdmins();
    }


    @PutMapping("/admin")
    public ResponseEntity<?> updateAdmin(@RequestBody PrivateAdminDTO privateAdminDTO){
        return adminService.updateAdmin(privateAdminDTO);
    }
    @PostMapping("/admin")
    public ResponseEntity<?> saveAdmin(@RequestBody SignupRequest signupRequest){
        return adminService.saveAdmin(signupRequest);
    }
}
