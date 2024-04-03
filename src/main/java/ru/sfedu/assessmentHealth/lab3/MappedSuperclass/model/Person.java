package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.assessmentHealth.model.BaseId;
import ru.sfedu.assessmentHealth.model.StatusPat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Root
@Entity(name = "Person")
public class Person extends BaseId {
    @Element
    @CsvBindByPosition(position = 1)
    @Column(name = "fio", nullable = false)
    protected String fio;
    @Element
    @CsvBindByPosition(position = 2)
    @Column(name = "age", nullable = false)
    protected Integer age;
    @Element
    @CsvBindByPosition(position = 3)
    @Column(name = "gender", nullable = false)
    protected String Gender;
    @Element
    @CsvBindByPosition(position = 4)
    @Column(name = "statusPat", nullable = false)
    protected StatusPat statusPat;

    public String getFio() {
        return fio;
    }

    public Person setFio(String fio) {
        this.fio = fio;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return Gender;
    }

    public Person setGender(String gender) {
        Gender = gender;
        return this;
    }

    public StatusPat getStatusPerson() {
        return statusPat;
    }

    public Person setStatusPerson(StatusPat statusPat) {
        this.statusPat = statusPat;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(fio, person.fio) && Objects.equals(age, person.age) && Objects.equals(Gender, person.Gender) && statusPat == person.statusPat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fio, age, Gender, statusPat);
    }

    @Override
    public String toString() {
        return "{" +
                " id=" + id +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", Gender='" + Gender + '\'' +
                ", statusPerson=" + statusPat +

                '}';
    }
}
