package ru.sfedu.assessmentHealth.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.assessmentHealth.CONST;

import java.net.Inet4Address;
import java.util.Objects;

@Root
public class Person {
    @Element()
    @CsvBindByPosition(position = 0)
    protected Integer Person_ID;
    @Element
    @CsvBindByPosition(position = 1)
    protected String Name;
    @Element
    @CsvBindByPosition(position = 2)
    protected String Surname;
    @Element
    @CsvBindByPosition(position = 3)
    protected String SecondName;
    @Element
    @CsvBindByPosition(position = 4)
    protected Integer Age;
    @Element
    @CsvBindByPosition(position = 5)
    protected char Gender;
    @Element
    @CsvBindByPosition(position = 6)
    protected StatusPerson Status;

    public Person(){
    }

    public Integer getPersonID(){return Person_ID;}
    public String getName(){return Name;}
    public String getSurname(){return Surname;}
    public String getSecondName(){return SecondName;}
    public int getAge() {return Age;}
    public char getGender() {return Gender;}
    public StatusPerson getStatus() {return Status;}


    public void setPerson_ID(Integer Person_ID){this.Person_ID = Person_ID;}
    public void setName(String Name){this.Name = Name;}
    public void setSurname(String Surname ){ this.Surname = Surname;}
    public void setSecondName(String SecondName) {this.SecondName = SecondName;}
    public void setAge(int Age){this.Age = Age;}
    public void setGender(char Gender){this.Gender = Gender;}
    public void setStatus(StatusPerson Status){this.Status = Status;}

    @Override
    public String toString() {
        return "Person{ " +
                "Person_ID = " + Person_ID +
                "Name = " + Name +
                ",Surname =  " + Surname +
                ",SecondName = " + SecondName +
                ", Age = " + Age +
                ",Gender = " + Gender +
                ",Status = " + Status +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Gender == person.Gender && Objects.equals(Person_ID, person.Person_ID) && Objects.equals(Name, person.Name) && Objects.equals(Surname, person.Surname) && Objects.equals(SecondName, person.SecondName) && Objects.equals(Age, person.Age) && Status == person.Status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Person_ID, Name, Surname, SecondName, Age, Gender, Status);
    }
}
