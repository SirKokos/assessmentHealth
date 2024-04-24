package ru.sfedu.assessmentHealth.lab4.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Preparation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer Id;
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
