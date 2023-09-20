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
@Table(name = "risks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Risk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "risk_id")
    private Integer riskid;
    @Column(name = "risk_status", columnDefinition = "text")
    private String risk_status;
    @Column(name = "risk_podstatus", columnDefinition = "text")
    private String risk_podstatus;
    @Column(name = "risk_reason", columnDefinition = "text")
    private String risk_reason;
    @Column(name = "risk_tipe", columnDefinition = "text")
    private String risk_tipe;
    @Column(name = "risk_name", columnDefinition = "text")
    private String risk_name;
    @Column(name = "risk_description", columnDefinition = "text")
    private String risk_description;
    @ManyToOne
    @JoinColumn(name = "risk_initiator_id")
    private Contact risk_initiator_id;
    @ManyToOne
    @JoinColumn(name = "risk_work_group_id")
    private WorkGroup risk_work_group_id;
    @ManyToOne
    @JoinColumn(name = "risk_executor_id")
    private Contact risk_executor_id;
    @ManyToOne
    @JoinColumn(name = "risk_device_id")
    private Device risk_device_id;
    @Column(name = "risk_registration_time", columnDefinition = "timestamp without time zone")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime risk_registration_time;
    @Column(name = "risk_complete_time", columnDefinition = "timestamp without time zone")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime risk_complete_time;
}
