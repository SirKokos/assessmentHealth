package ru.sfedu.assessmentHealth.lab2.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Person {
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "AGE", nullable = false)
    private Integer age;


    public Person(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
