package com.example.demo3.service;

import com.example.demo3.exceptions.ChangeNotFoundException;
import com.example.demo3.models.Change;
import com.example.demo3.repositories.ChangeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Primary
@Service
public class ChangeServiceImpl implements ChangeService{

    private static final String SQL_SLA = "UPDATE changes set change_end_time = :change_end_time where change_id_full = :id";

    private final ChangeRepository changeRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ChangeServiceImpl(ChangeRepository changeRepository, NamedParameterJdbcTemplate jdbcTemplate){
        this.changeRepository = changeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Change getChange (int change_id){
        return changeRepository.findById(change_id).orElseThrow(() -> new ChangeNotFoundException(change_id));
    }

    @Override
    public Change createChange (Change change){
        return changeRepository.save(change);
    }

    @Override
    public Object SLA (LocalDateTime change_end_time, String id){
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("change_end_time", change_end_time);
        jdbcTemplate.update(SQL_SLA, params);
        return changeRepository.findChangeByChangeidfull(id);
    }

    @Override
    public Change findChangeByChangeidfull (String idFull){return changeRepository.findChangeByChangeidfull(idFull);}

}
