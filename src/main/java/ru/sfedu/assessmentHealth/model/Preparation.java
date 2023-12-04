package ru.sfedu.assessmentHealth.model;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;

public class Preparation {
    private Integer Preparation_ID;
    private List<Integer> FkPreparationToDoctor;
    private String NamePrep;
    private Data DateEnd;
    private double Dosage;
    private  StatusVisitPreparation statusVisitPreparation;
    private String AboutPrep;
    private Integer CountPrep;


    public Integer getCountPrep(){return CountPrep;}
    public Integer getPreparation_ID(){return Preparation_ID;}
    public List<Integer> getFkPreparationToDoctor(){return FkPreparationToDoctor;}
    public String getNamePrep(){return NamePrep;}
    public Data getDateEnd(){return DateEnd;}
    public double getDosage(){return  Dosage;}
    public StatusVisitPreparation getStatusVisitPreparation(){return statusVisitPreparation;}
    public String getAboutPrep(){return AboutPrep;}


    public void setCountPrep(Integer CountPrep){this.CountPrep = CountPrep;}
    public void setPreparation_ID(Integer Preparation_ID){this.Preparation_ID = Preparation_ID;}
    public void setFkPreparationToDoctor(Integer FkPreparationToDoctor){this.FkPreparationToDoctor.add(FkPreparationToDoctor);}
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Preparation that)) return false;
        return Double.compare(that.Dosage, Dosage) == 0 && Objects.equals(Preparation_ID, that.Preparation_ID) && Objects.equals(FkPreparationToDoctor, that.FkPreparationToDoctor) && Objects.equals(NamePrep, that.NamePrep) && Objects.equals(DateEnd, that.DateEnd) && statusVisitPreparation == that.statusVisitPreparation && Objects.equals(AboutPrep, that.AboutPrep) && Objects.equals(CountPrep, that.CountPrep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Preparation_ID, FkPreparationToDoctor, NamePrep, DateEnd, Dosage, statusVisitPreparation, AboutPrep, CountPrep);
    }
}
