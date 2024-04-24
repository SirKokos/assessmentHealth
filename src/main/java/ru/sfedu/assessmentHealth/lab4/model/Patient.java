package ru.sfedu.assessmentHealth.lab4.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;


//@Builder
@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Patient extends Person {
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
