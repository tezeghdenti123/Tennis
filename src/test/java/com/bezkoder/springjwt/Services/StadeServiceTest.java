package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.DTO.StadeDTO;
import com.bezkoder.springjwt.models.Stade;
import com.bezkoder.springjwt.repository.StadeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")  // Use the test profile for H2
@Transactional
class StadeServiceTest {
    @Autowired
    private StadeRepository stadeRepository;
    @Autowired
    private StadeService stadeService;

    @Test
    void saveStade() {
        assertEquals(0,stadeRepository.findAll().size());
        StadeDTO stadeDTO=new StadeDTO();
        stadeDTO.setNum_stade(12);
        stadeDTO.setPrix(120.5F);
        stadeService.saveStade(stadeDTO);
        assertEquals(1,stadeRepository.findAll().size());
    }

    @Test
    void updateStade() {
        StadeDTO stadeDTO=new StadeDTO();
        stadeDTO.setNum_stade(14);
        stadeDTO.setPrix(120.5F);
        stadeService.updateStade(stadeDTO);
        assertEquals(0,stadeRepository.findAll().size());
        Stade stade=new Stade();
        stade.setNum_stade(12);
        stade.setPrix(122F);
        stade=stadeRepository.save(stade);
        stadeService.updateStade(stadeDTO);
        stade=stadeRepository.findById(stade.getId()).orElseThrow();
        assertEquals(12,stade.getNum_stade());
        stadeDTO.setId(stade.getId());
        stadeService.updateStade(stadeDTO);
        stade=stadeRepository.findById(stade.getId()).orElseThrow();
        assertEquals(14,stade.getNum_stade());

    }

    @Test
    void getStades() {
        Stade stade=new Stade();
        stade.setNum_stade(12);
        stade.setPrix(122F);
        stade=stadeRepository.save(stade);
        assertEquals(1,stadeService.getStades().size());
    }

    @Test
    void deleteStade() {
        Stade stade=new Stade();
        stade.setNum_stade(12);
        stade.setPrix(122F);
        stade=stadeRepository.save(stade);
        assertEquals(1,stadeService.getStades().size());
        stadeService.deleteStade(stade.getId());
        assertEquals(0,stadeService.getStades().size());

    }
}