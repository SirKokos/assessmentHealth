package ru.sfedu.assessmentHealth.model;

import java.net.Inet4Address;

public class Person {
//    protected Integer Person_ID;
    protected String Name;
    protected String Surname;
    protected String SecondName;
    protected int Age;
    protected char Gender;
    protected StatusPerson Status;

    public Person(){
    }

//    public Integer getPersonID(){return Person_ID;}
    public String getName(){return Name;}
    public String getSurname(){return Surname;}
    public String getSecondName(){return SecondName;}
    public int getAge() {return Age;}
    public char getGender() {return Gender;}
    public StatusPerson getStatus() {return Status;}


//    public void setPerson_ID(Integer Person_ID){this.Person_ID = Person_ID;}
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
