package ru.sfedu.assessmentHealth.model;

import javax.xml.crypto.Data;

public class Preparation {
    private String NamePrep;
    private Data DateEnd;
    private double Dosage;
    private  StatusVisitPreparation statusVisitPreparation;
    private String AboutPrep;


    public String getNamePrep(){return NamePrep;}
    public Data getDateEnd(){return DateEnd;}
    public double getDosage(){return  Dosage;}
    public StatusVisitPreparation getStatusVisitPreparation(){return statusVisitPreparation;}
    public String getAboutPrep(){return AboutPrep;}

    public void setNamePrep(String NamePrep){this.NamePrep = NamePrep;}
    public void setDateEnd(Data DateEnd){this.DateEnd = DateEnd;}
    public void setDosage(double Dosage){this.Dosage = Dosage;}
    public void setStatusVisitPreparation(StatusVisitPreparation statusVisitPreparation){
        this.statusVisitPreparation = statusVisitPreparation;}
    public void setAboutPrep(String AboutPrep){this.AboutPrep = AboutPrep;}

    @Override
    public String toString() {
        return "Preparation{NamePrep = " +
                ",DateEnd = " + DateEnd +
                ",Dosage = " + Dosage +
                ",statusVisitPreparation = " + statusVisitPreparation +
                ",AboutPrep = " + AboutPrep +
                "}";
    }
}
