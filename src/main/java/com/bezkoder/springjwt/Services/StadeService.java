package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.DTO.StadeDTO;
import com.bezkoder.springjwt.mappers.StadeMappers;
import com.bezkoder.springjwt.models.Stade;
import com.bezkoder.springjwt.repository.StadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StadeService {
    @Autowired
    private StadeRepository stadeRepository;

    public ResponseEntity<?> saveStade(StadeDTO stadeDTO){
        Stade stade= StadeMappers.INSTANCE.toEntity(stadeDTO);
        stadeRepository.save(stade);
        return ResponseEntity.ok("Stade saved successfuly!");
    }
    public ResponseEntity<?> updateStade(StadeDTO stadeDTO){
        if((stadeDTO.getId()!=null)&&(stadeRepository.existsById(stadeDTO.getId()))){
            Stade stade=StadeMappers.INSTANCE.toEntity(stadeDTO);
            Stade savedStade=stadeRepository.findById(stade.getId()).orElseThrow();
            savedStade.setNum_stade(stade.getNum_stade());
            savedStade.setPrix(stade.getPrix());
            stadeRepository.save(savedStade);
            return ResponseEntity.ok("saved successfuly!");
        }
        else{
            return ResponseEntity.ok("Id not found!");
        }


    }
    public List<StadeDTO>getStades(){
        List<Stade>stadeList=stadeRepository.findAll();
        List<StadeDTO>stadeDTOList=new ArrayList<>();
        for(int i=0;i<stadeList.size();i++){
            StadeDTO stadeDTO=StadeMappers.INSTANCE.toDTO(stadeList.get(i));
            stadeDTOList.add(stadeDTO);
        }
        return stadeDTOList;
    }
    public ResponseEntity<?> deleteStade(Long stadeId){
        if((stadeId!=null)&&(stadeRepository.existsById(stadeId))){
            stadeRepository.deleteById(stadeId);
            return ResponseEntity.ok("Deleted succefuly!");
        }
        return ResponseEntity.ok("Id not found!");
    }
}
