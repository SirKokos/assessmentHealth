package ru.sfedu.assessmentHealth.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import ru.sfedu.assessmentHealth.CONST;

import java.net.Inet4Address;

public class Person {

    @CsvBindByPosition(position = 0)
    protected Integer Person_ID;
    @CsvBindByPosition(position = 1)
    protected String Name;
    @CsvBindByPosition(position = 2)
    protected String Surname;
    @CsvBindByPosition(position = 3)
    protected String SecondName;
    @CsvBindByPosition(position = 4)
    protected Integer Age;
    @CsvBindByPosition(position = 5)
    protected char Gender;
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
        return "Person{ Name = " + Name +
                ",Surname =  " + Surname +
                ",SecondName = " + SecondName +
                ", Age = " + Age +
                ",Gender = " + Gender +
                ",Status = " + Status +
                "}";
    }
}
