package com.example.demo3.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contacts_work_groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactWorkGroup {
    @Id
    @ManyToOne
    @JoinColumn(name = "contacts_contact_id_first")
    private Contact contacts_contact_id_first;

    @Id
    @ManyToOne
    @JoinColumn(name = "work_groups_group_id")
    private WorkGroup work_groups_group_id;

}
