package ru.sfedu.assessmentHealth.lab3.SingleTable.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(Id, person.Id) && Objects.equals(Fio, person.Fio) && Objects.equals(Age, person.Age) && Objects.equals(Gender, person.Gender) && statusPerson == person.statusPerson;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Fio, Age, Gender, statusPerson);
    }
}
