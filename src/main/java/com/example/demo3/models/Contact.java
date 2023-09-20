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
@Table (name = "contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id_first")
    private Integer contact_id_first;
    @Column(name = "full_name", columnDefinition = "text")
    private String fullname;
    @Column(name = "contact_position", columnDefinition = "text")
    private String contact_position;
    @Column(name = "contact_number_phone", columnDefinition = "text")
    private String contact_number_phone;
    @Column(name = "contact_id", columnDefinition = "text")
    private String contact_id;
    @Column(name = "contact_adress", columnDefinition = "text")
    private String contact_adress;
    @Column(name = "contact_full_name", columnDefinition = "text")
    private String contact_full_name;
    @Column(name = "contact_division", columnDefinition = "text")
    private String contact_division;
    @Column(name = "contact_birthday", columnDefinition = "date")
    private Date contact_birthday;

    @OneToMany(targetEntity = Risk.class,mappedBy = "risk_initiator_id")
    private Set<Risk> risks_initiator;

    @OneToMany(targetEntity = Risk.class,mappedBy = "risk_executor_id")
    private Set<Risk> risks_executor;

    @OneToMany(targetEntity = Change.class,mappedBy = "change_initiator_id")
    private Set<Change> change_initiator;

    @OneToMany(targetEntity = Change.class,mappedBy = "change_executor_id")
    private Set<Change> change_executor;

    @OneToMany(targetEntity = Device.class,mappedBy = "device_administrator_id")
    private Set<Device> device_administrator;

    @OneToMany(targetEntity = WorkGroup.class,mappedBy = "group_administrator_id")
    private Set<WorkGroup> group_administrator;

    @OneToMany(targetEntity = ContactWorkGroup.class,mappedBy = "contacts_contact_id_first")
    private Set<ContactWorkGroup> contacts_contact;

}
