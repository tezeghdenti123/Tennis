package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.PublicCoachDTO;
import com.bezkoder.springjwt.Services.CoachService;
import com.bezkoder.springjwt.models.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class CoachController {
    @Autowired
    private CoachService coachService;


    @PostMapping("/coach")
    public ResponseEntity<?> saveCoach(@RequestBody PublicCoachDTO publicCoachDTO){
        return coachService.saveCoach(publicCoachDTO);
    }
    @GetMapping("/coachs")
    public List<Coach> getCoachs(){
        return coachService.getCoachs();
    }
}
