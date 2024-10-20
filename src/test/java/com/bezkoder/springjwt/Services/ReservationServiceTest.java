package com.bezkoder.springjwt.Services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;

    @Test
    void isCurentWeek() {
        Boolean result=reservationService.isCurentWeek(LocalDate.now());
        assertEquals(result,true);
        result=reservationService.isCurentWeek(LocalDate.of(2024,10,17));
        assertEquals(false,result);
    }
}