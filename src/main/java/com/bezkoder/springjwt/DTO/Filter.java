package com.bezkoder.springjwt.DTO;

import com.bezkoder.springjwt.models.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Filter {
    private LocalDate date;


    private Long adminId;

    private Long clientId;

    private Long stadeId;

    private Long groupeId;
    private Long coachId;

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Long getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(Long groupeId) {
        this.groupeId = groupeId;
    }

    public Long getStadeId() {
        return stadeId;
    }

    public void setStadeId(Long stadeId) {
        this.stadeId = stadeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }





    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
