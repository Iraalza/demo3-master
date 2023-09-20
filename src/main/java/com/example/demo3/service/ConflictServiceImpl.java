package com.example.demo3.service;

import com.example.demo3.exceptions.ConflictNotFoundException;
import com.example.demo3.models.Conflict;
import com.example.demo3.models.Device;
import com.example.demo3.repositories.ConflictRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Primary
@Service
public class ConflictServiceImpl implements ConflictService{
    private static final String SQL_FIND_CONFLICT = "select change_id from changes where change_start_time = :change_start_time and change_end_time = :change_end_time and change_device_id = :change_device_id and change_id_full != :change_id_full";
    private static final String SQL_FIND_ID_BY_FULL_ID = "select change_id from changes where change_id_full = :change_id_full";
    private static final String SQL_UPDATE_NO_CONFLICTS = "UPDATE changes set change_conflicts = false where change_id = :change_id";
    private static final String SQL_UPDATE_YES_CONFLICTS = "UPDATE changes set change_conflicts = true where change_id = :change_id";
    private static final String SQL_INSERT_CONFLICTS = "INSERT INTO conflicts (conflict_change_first_id, conflict_change_second_id, conflict_time_start, conflict_time_end, conflict_device_id) values (:conflict_change_first_id, :conflict_change_second_id, :conflict_time_start, :conflict_time_end, :conflict_device_id)";
    private final ConflictRepository conflictRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public ConflictServiceImpl (ConflictRepository conflictRepository, NamedParameterJdbcTemplate jdbcTemplate){
        this.conflictRepository = conflictRepository;
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Conflict getConflict (int conflictid){
        return conflictRepository.findById(conflictid).orElseThrow(() -> new ConflictNotFoundException(conflictid));
    }
    @Override
    public Conflict createConflict (Conflict newConflict){
        return conflictRepository.save(newConflict);
    }
    @Override
    public Object foundConflicts (String fullid, LocalDateTime change_start_time, LocalDateTime change_end_time, Device device_id){
        Pattern pattern = Pattern.compile("\\d+");
        Integer id_device = device_id.getDevice_id();
        Integer find_id = 0;
        var params = new MapSqlParameterSource();
        params.addValue("change_id_full", fullid);
        params.addValue("change_end_time", change_end_time);
        params.addValue("change_start_time", change_start_time);
        params.addValue("change_device_id", id_device);
        List<Map<String, Object>> ListOfConflicts = jdbcTemplate.queryForList(SQL_FIND_CONFLICT, params);
        List<Map<String, Object>> newConflicts = jdbcTemplate.queryForList(SQL_FIND_ID_BY_FULL_ID, params);
        Matcher matcher = pattern.matcher(newConflicts.get(0).toString());
        while (matcher.find()) {
            find_id = Integer.valueOf(matcher.group());
        }
        if(ListOfConflicts.size() == 0){
            var paramsUpdate = new MapSqlParameterSource();
            paramsUpdate.addValue("change_id", find_id);
            jdbcTemplate.update(SQL_UPDATE_NO_CONFLICTS, paramsUpdate);
        }
        else {
            var paramsUpdate = new MapSqlParameterSource();
            paramsUpdate.addValue("change_id", find_id);
            jdbcTemplate.update(SQL_UPDATE_YES_CONFLICTS, paramsUpdate);
            System.out.println(ListOfConflicts.size());
            System.out.println(ListOfConflicts);
            //System.out.println(ListOfConflicts.get(1));
            for (int i = 0; i < ListOfConflicts.size(); i++) {
                Matcher matcherr = pattern.matcher(ListOfConflicts.get(i).toString());
                while (matcherr.find()) {
                    Integer find_id_second = Integer.valueOf(matcherr.group());
                    //System.out.println(matcher.group());
                    System.out.println(find_id_second);
                    var paramsUpdateSecond = new MapSqlParameterSource();
                    paramsUpdateSecond.addValue("change_id", find_id_second);
                    jdbcTemplate.update(SQL_UPDATE_YES_CONFLICTS, paramsUpdateSecond);
                    var paramsInsert = new MapSqlParameterSource();
                    paramsInsert.addValue("conflict_change_first_id", find_id);
                    paramsInsert.addValue("conflict_change_second_id", find_id_second);
                    paramsInsert.addValue("conflict_time_start", change_start_time);
                    paramsInsert.addValue("conflict_time_end", change_end_time);
                    paramsInsert.addValue("conflict_device_id", id_device);
                    jdbcTemplate.update(SQL_INSERT_CONFLICTS, paramsInsert);
                }
            }
        }
        return ListOfConflicts;
    }
}
