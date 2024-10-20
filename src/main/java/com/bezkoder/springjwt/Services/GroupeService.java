package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.models.Groupe;
import com.bezkoder.springjwt.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeService {
    @Autowired
    private GroupeRepository groupeRepository;

    public ResponseEntity<?> saveGroupe(Groupe groupe){
        groupeRepository.save(groupe);
        return ResponseEntity.ok("Saved!");
    }

    public List<Groupe> getGroups() {
        return groupeRepository.findAll();
    }
}
