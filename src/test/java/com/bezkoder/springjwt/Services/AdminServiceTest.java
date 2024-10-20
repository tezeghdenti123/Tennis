package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.DTO.PrivateAdminDTO;
import com.bezkoder.springjwt.models.Admin;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Reservation;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.repository.AdminRepository;
import com.bezkoder.springjwt.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")  // Use the test profile for H2
@Transactional
class AdminServiceTest {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;



    @Test
    void getAllAdmins() {
        //Given: one entity saved in database
        Admin admin=new Admin();
        admin.setEmail("Test@gmail.com");
        admin.setName("Test");
        admin.setUsername("Test123456");
        admin.setPassword("Test2019");
        admin.setPhone("94102105");
        Set<Role> roles=new HashSet<>();
        Role role=roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow();
        roles.add(role);
        admin.setRoles(roles);
        adminRepository.save(admin);
        //When: calling the function gatAllAdmins
        int nbAdmins=adminService.getAllAdmins().size();
        assertEquals(1,nbAdmins);
    }

    @Test
    void updateAdmin() {
        Admin admin=new Admin();
        admin.setName("Test");
        admin.setEmail("Test@gmail.com");
        admin.setUsername("Test");
        admin.setPassword("Test123456");
        admin.setPhone("94102105");
        Reservation reservation=new Reservation();
        reservation.setDate(LocalDate.now());
        //reservation.setHeure_fin("12,25");
        List<Reservation>reservationList=new ArrayList<>();
        reservationList.add(reservation);
        admin.setReservationList(reservationList);
        admin=adminRepository.save(admin);
        assertNotEquals(null,admin.getReservationList().get(0).getId());
        PrivateAdminDTO privateAdminDTO=new PrivateAdminDTO();
        privateAdminDTO.setId(admin.getId());
        privateAdminDTO.setName("Test1");
        privateAdminDTO.setEmail("Test1@gmail.com");
        privateAdminDTO.setPhone("99999999");
        privateAdminDTO.setPassword("test12345");
        privateAdminDTO.setUsername("Test1");
        adminService.updateAdmin(privateAdminDTO);
        admin=adminRepository.findById(admin.getId()).orElseThrow();
        assertEquals("Test1",admin.getName());
        assertEquals("Test1@gmail.com",admin.getEmail());
        assertEquals("99999999",admin.getPhone());
        assertEquals("Test1",admin.getUsername());
    }

    @Test
    void deleteAdmin() {
        Admin admin=new Admin();
        admin.setEmail("Test@gmail.com");
        admin.setName("Test");
        admin.setUsername("Test123456");
        admin.setPassword("Test2019");
        admin.setPhone("94102105");
        Set<Role> roles=new HashSet<>();
        Role role=roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow();
        roles.add(role);
        admin.setRoles(roles);
        admin=adminRepository.save(admin);
        assertEquals(1,adminRepository.findAll().size());
        adminService.deleteAdmin(admin.getId());
        assertEquals(0,adminRepository.findAll().size());

    }
}