package com.example.demo3.controllers;

import com.example.demo3.models.Change;
import com.example.demo3.models.Device;
import com.example.demo3.repositories.ChangeRepository;
import com.example.demo3.repositories.ConflictRepository;
import com.example.demo3.service.ChangeService;
import com.example.demo3.service.ConflictService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
public class ChangeController {

    //public LocalDateTime change_end_time = LocalDateTime.parse("1999-01-08T04:05:06");

    private final ChangeRepository changeRepository;

    private final ChangeService changeService;

    private final ConflictRepository conflictRepository;

    private final ConflictService conflictService;

    public ChangeController(ChangeRepository changeRepository, ChangeService changeService, ConflictRepository conflictRepository, ConflictService conflictService){
        this.changeRepository = changeRepository;
        this.changeService = changeService;
        this.conflictRepository = conflictRepository;
        this.conflictService = conflictService;
    }

    @GetMapping("/changes")
    Iterable<Change> all(){return changeRepository.findAll();}

    @GetMapping("/change/{id}")
    public Change getChange(@PathVariable int id){
        return changeService.getChange(id);
    }

    @PostMapping("/addChange")
    Change newChange(@RequestBody Change newChange){
        changeService.createChange(newChange);
        LocalDateTime change_start_time = newChange.getChange_start_time();
        String change_priority = newChange.getChange_priority();
        String change_critical = newChange.getChange_critical();
        String fullid = newChange.getChangeidfull();
        Device device_id = newChange.getChange_device_id();
        LocalDateTime change_end_time = LocalDateTime.parse("1999-01-08T04:05:06");
        System.out.println(change_end_time);
        change_end_time = SLA(change_start_time, change_critical, change_priority, fullid, change_end_time);
        Change.foo();
        System.out.println(change_end_time);
        foundConflict(fullid, change_start_time, change_end_time, device_id );
        return changeService.findChangeByChangeidfull(fullid);
    }

    public LocalDateTime SLA(LocalDateTime change_start_time, String change_critical, String change_priority, String id, LocalDateTime change_end_time){
        if (Objects.equals(change_priority, "Низкий")){
            if (Objects.equals(change_critical, "Office Productivity")){
                change_end_time = change_start_time.plusHours(99);
            }
            else if (Objects.equals(change_critical, "Business Operational")){
                change_end_time = change_start_time.plusHours(99);
            }
            else if (Objects.equals(change_critical, "Business Critical")){
                change_end_time = change_start_time.plusHours(60);
            }
            else if (Objects.equals(change_critical, "Mission Critical")){
                change_end_time = change_start_time.plusHours(48);
            }
            else if (Objects.equals(change_critical, "Mission Critical +")){
                change_end_time = change_start_time.plusHours(48);
            }
        }
        else if (Objects.equals(change_priority, "Средний")){
            if (Objects.equals(change_critical, "Office Productivity")){
                change_end_time = change_start_time.plusHours(99);
            }
            else if (Objects.equals(change_critical, "Business Operational")){
                change_end_time = change_start_time.plusHours(26);
            }
            else if (Objects.equals(change_critical, "Business Critical")){
                change_end_time = change_start_time.plusHours(20);
            }
            else if (Objects.equals(change_critical, "Mission Critical")){
                change_end_time = change_start_time.plusHours(12);
            }
            else if (Objects.equals(change_critical, "Mission Critical +")){
                change_end_time = change_start_time.plusHours(12);
            }
        }
        else if (Objects.equals(change_priority, "Высокий")){
            if (Objects.equals(change_critical, "Office Productivity")){
                change_end_time = change_start_time.plusHours(99);
            }
            else if (Objects.equals(change_critical, "Business Operational")){
                change_end_time = change_start_time.plusHours(9);
            }
            else if (Objects.equals(change_critical, "Business Critical")){
                change_end_time = change_start_time.plusHours(7);
            }
            else if (Objects.equals(change_critical, "Mission Critical")){
                change_end_time = change_start_time.plusHours(6);
            }
            else if (Objects.equals(change_critical, "Mission Critical +")){
                change_end_time = change_start_time.plusHours(6);
            }
        }
        else if (Objects.equals(change_priority, "Очень высокий")){
            if (Objects.equals(change_critical, "Office Productivity")){
                change_end_time = change_start_time.plusHours(99);
            }
            else if (Objects.equals(change_critical, "Business Operational")){
                change_end_time = change_start_time.plusHours(4);
            }
            else if (Objects.equals(change_critical, "Business Critical")){
                change_end_time = change_start_time.plusHours(2);
            }
            else if (Objects.equals(change_critical, "Mission Critical")){
                change_end_time = change_start_time.plusHours(2);
            }
            else if (Objects.equals(change_critical, "Mission Critical +")){
                change_end_time = change_start_time.plusHours(2);
            }
        }
        System.out.println(change_end_time);
        changeService.SLA(change_end_time, id );
        return change_end_time;
    }

    public void foundConflict(String fullid, LocalDateTime change_start_time, LocalDateTime change_end_time, Device device_id ){
        conflictService.foundConflicts (fullid, change_start_time, change_end_time, device_id);
    }
}
