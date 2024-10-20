package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.DTO.PrivateClientDTO;
import com.bezkoder.springjwt.DTO.PublicClientDTO;
import com.bezkoder.springjwt.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/clients")
    public List<PublicClientDTO> getClients(){
        return clientService.getClients();
    }

    @PostMapping("/client")
    public ResponseEntity<?> saveClient(@RequestBody PrivateClientDTO privateClientDTO){
        return  clientService.saveClient(privateClientDTO);
    }
}
