package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model;



import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Doctor extends  Person{
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
