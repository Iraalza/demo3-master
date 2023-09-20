package com.example.demo3.service;

import com.example.demo3.models.Change;

import java.time.LocalDateTime;

public interface ChangeService {

    Change getChange (int change_id);

    Change createChange (Change change);

    Object SLA (LocalDateTime change_end_time, String id);

    Change findChangeByChangeidfull (String idFull);
}
