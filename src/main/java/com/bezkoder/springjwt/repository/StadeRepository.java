package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Stade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadeRepository extends JpaRepository<Stade,Long> {
}
