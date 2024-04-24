package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model;

import lombok.*;


import javax.persistence.*;


//@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Setter
@Getter
@MappedSuperclass
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
