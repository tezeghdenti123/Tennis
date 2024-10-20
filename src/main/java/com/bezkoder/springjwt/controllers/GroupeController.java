package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.Services.GroupeService;
import com.bezkoder.springjwt.models.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GroupeController {
    @Autowired
    private GroupeService groupeService;

    @PostMapping("groupe")
    public ResponseEntity<?>saveGroupe(@RequestBody Groupe groupe){
        return groupeService.saveGroupe(groupe);
    }


    @GetMapping("groupes")
    public List<Groupe> getGroupes(){
        return groupeService.getGroups();
    }
}
