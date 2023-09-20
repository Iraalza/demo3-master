package com.example.demo3.sla;

import com.example.demo3.service.ChangeService;

import java.time.LocalDateTime;
import java.util.Objects;

public class sla {

    private static ChangeService changeService;

    public sla (ChangeService changeService){
        sla.changeService = changeService;
    }

    public void SLA(LocalDateTime change_start_time, String change_critical, String change_priority, String id){

        LocalDateTime change_end_time = change_start_time;

        if (Objects.equals(change_priority, "Низкий")){
            if (Objects.equals(change_critical, "Office Productivity")){
                change_end_time = change_start_time;
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
                change_end_time = change_start_time;
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
                change_end_time = change_start_time;
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
                change_end_time = change_start_time;
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
        //changeService.SLA(change_end_time, id );
    }
}
