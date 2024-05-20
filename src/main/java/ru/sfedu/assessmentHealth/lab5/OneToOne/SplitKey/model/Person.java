package ru.sfedu.assessmentHealth.lab5.OneToOne.SplitKey.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;


@ToString
@NoArgsConstructor
@Setter
@Getter
@MappedSuperclass
public class Person {
    @Id
    @Column(name = "person_id")
    protected Integer Id;
    @Column
    protected String Fio;
    @Column
    protected Integer Age;
    @OneToOne(fetch = FetchType.EAGER,optional = false)
    @PrimaryKeyJoinColumn
    protected Address shippingAddress;
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

