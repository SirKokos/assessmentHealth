package ru.sfedu.assessmentHealth.lab2.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Person {
    @Column(name = "Person_name")
    private String name;
    @Column
    private Integer age;

}
