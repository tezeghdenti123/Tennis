package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.DTO.PublicUserDTO;
import com.bezkoder.springjwt.mappers.PublicUserMappers;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<PublicUserDTO> getUsers() {
        List<User> userList=userRepository.findAll();
        List<PublicUserDTO> publicUserDTOList=new ArrayList<>();
        for(int i=0;i<userList.size();i++){
            PublicUserDTO publicUserDTO= PublicUserMappers.INSTANCE.toDTO(userList.get(i));
            publicUserDTOList.add(publicUserDTO);
        }
        return publicUserDTOList;
    }
}
