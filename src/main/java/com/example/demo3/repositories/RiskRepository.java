package com.example.demo3.repositories;

import com.example.demo3.models.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Integer>{
    Optional<Risk> findById(Integer riskid);
}
