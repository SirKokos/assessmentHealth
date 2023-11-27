package ru.sfedu.assessmentHealth.model;

public class Doctor extends Person{
    private int Experience;
    private double AvgPatient;
    private String Qualification;
    private  String Specialization;

    public int getExperience(){return Experience;}
    public double getAvgPatient() {return AvgPatient;}
    public String getQualification(){return Qualification;}
    public String getSpecialization(){return Specialization;}

    public void setExperience(int Experience){this.Experience = Experience;}
    public void setAvgPatient(double AvgPatient) {this.AvgPatient =  AvgPatient;}
    public void setQualification(String Qualification){this.Qualification = Qualification;}
    public void setSpecialization(String Specialization){this.Specialization = Specialization;}

    @Override
    public String toString() {
        return "Person{ Name = " + Name +
                ",Surname =  " + Surname +
                ",SecondName = " + SecondName +
                ",Age = " + Age +
                ",Gender = " + Gender +
                ",Status = " + Status +
                ",Experience = " + Experience +
                ",AvgPatient = " + AvgPatient +
                ",Qualification = " + Qualification +
                ",Specialization = " + Specialization +
                "}";
    }
}
