package ru.sfedu.assessmentHealth.utils;

import java.util.Objects;

public class PersTestUnit {
     public String name;
    public  int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersTestUnit testUnit)) return false;
        return age == testUnit.age && Objects.equals(name, testUnit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
