package com.example.demo3.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "conflicts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Conflict {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "conflict_id")
    private Integer conflictid;
    @ManyToOne
    @JoinColumn(name = "conflict_change_first_id")
    private Change conflict_change_first_id;
    @ManyToOne
    @JoinColumn(name = "conflict_change_second_id")
    private Change conflict_change_second_id;
    @Column(name = "conflict_time_start", columnDefinition = "timestamp without time zone")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime conflict_time_start;
    @Column(name = "conflict_time_end", columnDefinition = "timestamp without time zone")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime conflict_time_end;
    @ManyToOne
    @JoinColumn(name = "conflict_device_id")
    private Device conflict_device_id;
}
