package ru.sfedu.assessmentHealth.lab3.TablePerClass.model;

import lombok.*;

import javax.persistence.*;


//@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
