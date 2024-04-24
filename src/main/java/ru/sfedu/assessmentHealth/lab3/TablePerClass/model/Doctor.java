package ru.sfedu.assessmentHealth.lab3.TablePerClass.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;


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
    protected  String specialization;

//    protected List<Preparation> linkPreparation = new ArrayList<>();
//    protected List<Schedule> linkSchedule = new ArrayList<>();
}
