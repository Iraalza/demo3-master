package com.example.demo3.repositories;

import com.example.demo3.models.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChangeRepository extends JpaRepository<Change, Integer>{
    Optional<Change> findById(Integer changeid);
    Change findChangeByChangeidfull (String changeidfull);
}
