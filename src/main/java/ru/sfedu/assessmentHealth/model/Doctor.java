package ru.sfedu.assessmentHealth.model;

import java.util.Objects;

public class Doctor extends Person{
    protected Integer Doctor_ID;
    private int Experience;
    private double AvgPatient;
    private String Qualification;
    private  String Specialization;


    public Integer getDoctor_ID(){return Doctor_ID;}
    public int getExperience(){return Experience;}
    public double getAvgPatient() {return AvgPatient;}
    public String getQualification(){return Qualification;}
    public String getSpecialization(){return Specialization;}

    public void setDoctor_ID(Integer Doctor_ID){this.Doctor_ID = Doctor_ID;}
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor doctor)) return false;
        return Experience == doctor.Experience && Double.compare(doctor.AvgPatient, AvgPatient) == 0 && Objects.equals(Qualification, doctor.Qualification) && Objects.equals(Specialization, doctor.Specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Experience, AvgPatient, Qualification, Specialization);
    }
}
