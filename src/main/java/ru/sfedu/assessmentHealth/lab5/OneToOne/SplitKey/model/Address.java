package ru.sfedu.assessmentHealth.lab5.OneToOne.SplitKey.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column
    protected String street;
    @Column
    protected String zipCode;
    @Column
    protected  String city;
}
