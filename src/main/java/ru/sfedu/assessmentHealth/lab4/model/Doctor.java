package ru.sfedu.assessmentHealth.lab4.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Doctor extends Person {
    @Column
    protected Integer experience;
    @Column
    protected Double avgPatient;
    @Column
    protected String qualification;
    @Column
    protected String specialization;

    @ElementCollection
    @OrderColumn
    protected List<Preparation> linkPreparation = new ArrayList<>();

    @ElementCollection
    protected Set<Schedule> linkSchedule = new HashSet<>();
}
