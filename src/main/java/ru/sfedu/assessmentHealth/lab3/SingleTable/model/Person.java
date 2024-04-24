package ru.sfedu.assessmentHealth.lab3.SingleTable.model;

import lombok.*;

import javax.persistence.*;


//@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Integer Id;
    @Column
    protected String Fio;
    @Column
    protected Integer Age;
    @Column
    protected String Gender;
    @Enumerated(EnumType.STRING)
    protected StatusPerson statusPerson;

}
