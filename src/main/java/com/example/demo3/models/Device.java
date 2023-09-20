package com.example.demo3.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "devices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_id")
    private Integer device_id;

    @Column(name = "device_id_full", columnDefinition = "text")
    private String device_id_full;

    @Column(name = "device_name", columnDefinition = "text")
    private String device_name;

    @Column(name = "device_kategory", columnDefinition = "text")
    private String device_kategory;

    @Column(name = "device_type", columnDefinition = "text")
    private String device_type;

    @Column(name = "device_lc_stage", columnDefinition = "text")
    private String device_lc_stage;

    @Column(name = "device_description", columnDefinition = "text")
    private String device_description;

    @ManyToOne
    @JoinColumn(name = "device_administrator_id")
    private Contact device_administrator_id;

    @OneToMany(targetEntity = Risk.class,mappedBy = "risk_device_id")
    private Set<Risk> risk_device;

    @OneToMany(targetEntity = Conflict.class,mappedBy = "conflict_device_id")
    private Set<Conflict> conflict_device;

    @OneToMany(targetEntity = Change.class,mappedBy = "change_device_id")
    private Set<Change> change_device;
}
