package com.example.demo3.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "work_groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WorkGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private Integer group_id;

    @Column(name = "group_name", columnDefinition = "text")
    private String group_name;

    @Column(name = "group_id_full", columnDefinition = "text")
    private String group_id_full;

    @ManyToOne
    @JoinColumn(name = "group_administrator_id")
    private Contact group_administrator_id;

    @OneToMany(targetEntity = ContactWorkGroup.class,mappedBy = "work_groups_group_id")
    private Set<ContactWorkGroup> work_groups_group;

    @OneToMany(targetEntity = Risk.class,mappedBy = "risk_work_group_id")
    private Set<Risk> risk_work_group;

    @OneToMany(targetEntity = Change.class,mappedBy = "change_work_group_id")
    private Set<Change> change_work_group;
}
