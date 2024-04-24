package ru.sfedu.assessmentHealth.lab3.Joined.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(Id, person.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
