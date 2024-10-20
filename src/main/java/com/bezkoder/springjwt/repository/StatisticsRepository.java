package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,Long> {
}
