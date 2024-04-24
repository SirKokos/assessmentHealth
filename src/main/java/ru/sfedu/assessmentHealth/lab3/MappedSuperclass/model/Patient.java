package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model;



import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;


//@Builder
@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Patient extends Person{
    @Column
    protected Double cellsBlood;
    @Column
    protected Double hemoglobin;
    @Column
    protected Double platelets;
    @Column
    protected Double glucose;
    @Column
    protected Double cholesterol;
    @Column
    protected StatusVisit statusVisit;


}
