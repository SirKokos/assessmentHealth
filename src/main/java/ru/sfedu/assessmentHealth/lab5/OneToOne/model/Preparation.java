package ru.sfedu.assessmentHealth.lab5.OneToOne.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;


@ToString
@NoArgsConstructor
@Setter
@Getter

@Entity

public class Preparation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer Id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Doctor_id",nullable = false)
    protected Doctor doctor;
    @Column
    protected String NamePrep;
    @Column
    protected Date DateEnd;
    @Column
    protected Double Dosage;
    @Enumerated(EnumType.STRING)
    protected StatusPreparation StatusVisitPreparation;
    @Column
    protected String AboutPrep;



}
