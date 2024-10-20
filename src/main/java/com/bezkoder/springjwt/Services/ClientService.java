package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.DTO.PrivateClientDTO;
import com.bezkoder.springjwt.DTO.PublicClientDTO;
import com.bezkoder.springjwt.mappers.PrivateClientMappers;
import com.bezkoder.springjwt.mappers.PublicClientMappers;
import com.bezkoder.springjwt.models.Client;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.ClientRepository;
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
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;

    public List<PublicClientDTO> getClients(){
        List<Client>clientList= clientRepository.findAll();
        List<PublicClientDTO>publicClientDTOList=new ArrayList<>();
        for(int i=0;i<clientList.size();i++){
            PublicClientDTO publicClientDTO= PublicClientMappers.INSTANCE.toDTO(clientList.get(i));
            publicClientDTOList.add(publicClientDTO);
        }
        return publicClientDTOList;
    }

    public ResponseEntity<?> saveClient(PrivateClientDTO privateClientDTO) {
        if (userRepository.existsByUsername(privateClientDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(privateClientDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Client client= PrivateClientMappers.INSTANCE.toEntity(privateClientDTO);
        client.setPassword(encoder.encode(client.getPassword()));
        Set<Role> roles=new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        client.setRoles(roles);
        if(client.getGroupe().getId()==null){
            client.setGroupe(null);
        }
        clientRepository.save(client);
        return ResponseEntity.ok("Saved");
    }
}
