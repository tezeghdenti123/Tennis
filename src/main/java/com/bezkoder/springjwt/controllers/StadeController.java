package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.StadeDTO;
import com.bezkoder.springjwt.Services.StadeService;
import com.bezkoder.springjwt.models.Stade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class StadeController {
    @Autowired
    private StadeService stadeService;

    @GetMapping("/stades")
    public List<StadeDTO> getStades(){
        return stadeService.getStades();
    }
}
