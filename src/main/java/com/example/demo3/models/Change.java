package com.example.demo3.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "changes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Change {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "change_id")
    private Integer changeid;

    @Column(name = "change_id_full", columnDefinition = "text")
    private String changeidfull;

    @Column(name = "change_status", columnDefinition = "text")
    private String change_status;

    @Column(name = "change_name", columnDefinition = "text")
    private String change_name;

    @Column(name = "change_description", columnDefinition = "text")
    private String change_description;

    @Column(name = "change_resolution", columnDefinition = "text")
    private String change_resolution;

    @Column(name = "change_conflicts")
    private Boolean change_conflicts;

    @Column(name = "change_priority")
    private String change_priority;

    @Column(name = "change_critical")
    private String change_critical;

    @ManyToOne
    @JoinColumn(name = "change_initiator_id")
    private Contact change_initiator_id;

    @ManyToOne
    @JoinColumn(name = "change_executor_id")
    private Contact change_executor_id;

    @ManyToOne
    @JoinColumn(name = "change_work_group_id")
    private WorkGroup change_work_group_id;

    @ManyToOne
    @JoinColumn(name = "change_device_id")
    private Device change_device_id;

    @Column(name = "change_start_time", columnDefinition = "timestamp without time zone")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime change_start_time;

    @Column(name = "change_end_time", columnDefinition = "timestamp without time zone")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime change_end_time;

    @OneToMany(targetEntity = Conflict.class,mappedBy = "conflict_change_first_id")
    private Set<Conflict> conflict_change_first;

    @OneToMany(targetEntity = Conflict.class,mappedBy = "conflict_change_second_id")
    private Set<Conflict> conflict_change_second;

    public static void foo(){
        System.out.println("Хуйня");
    }
}
