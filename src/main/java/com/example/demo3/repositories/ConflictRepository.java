package com.example.demo3.repositories;

import com.example.demo3.models.Conflict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Integer> {
    Optional<Conflict> findById (Integer conflictid);

}
