package com.example.demo3.service;

import com.example.demo3.models.Conflict;
import com.example.demo3.models.Device;

import java.time.LocalDateTime;

public interface ConflictService {

    Conflict getConflict (int conflictid);

    Conflict createConflict (Conflict conflict);

    Object foundConflicts (String fullid, LocalDateTime change_start_time, LocalDateTime change_end_time, Device device_id);
}
