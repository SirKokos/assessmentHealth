package ru.sfedu.assessmentHealth.lab4.model;

import lombok.*;

import javax.persistence.*;


//@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
