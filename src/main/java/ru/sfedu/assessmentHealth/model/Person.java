package ru.sfedu.assessmentHealth.model;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Objects;

@Root
public class Person extends BaseId{
    @Element
    @CsvBindByPosition(position = 1)
    protected String fio;
    @Element
    @CsvBindByPosition(position = 2)
    protected Integer age;
    @Element
    @CsvBindByPosition(position = 3)
    protected String Gender;
    @Element
    @CsvBindByPosition(position = 4)
    protected StatusPerson statusPerson;

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

    public StatusPerson getStatusPerson() {
        return statusPerson;
    }

    public Person setStatusPerson(StatusPerson statusPerson) {
        this.statusPerson = statusPerson;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(fio, person.fio) && Objects.equals(age, person.age) && Objects.equals(Gender, person.Gender) && statusPerson == person.statusPerson;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fio, age, Gender, statusPerson);
    }

    @Override
    public String toString() {
        return "{" +
                " id=" + id +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", Gender='" + Gender + '\'' +
                ", statusPerson=" + statusPerson +

                '}';
    }
}
