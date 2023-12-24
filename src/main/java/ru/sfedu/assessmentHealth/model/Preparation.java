package ru.sfedu.assessmentHealth.model;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Root
public class Preparation {
    @Element
    @CsvBindByPosition(position = 0)
    private Integer Preparation_ID;
//    @CsvBindAndSplitByName(elementType = Integer.class, splitOn = "/", writeDelimiter = ";")
    @Element
    @CsvBindByPosition(position = 1)
    protected Integer FkPreparationToDoctor;
    @Element
    @CsvBindByPosition(position = 2)
    private String NamePrep;
    @Element
    @CsvBindByPosition(position = 3)
    @CsvDate("yyyy-MM-dd")
    private Date DateEnd;
    @Element
    @CsvBindByPosition(position = 4)
    private double Dosage;
    @Element
    @CsvBindByPosition(position = 5)
    private  StatusVisitPreparation statusVisitPreparation;
    @Element
    @CsvBindByPosition(position = 6)
    private String AboutPrep;
    @Element
    @CsvBindByPosition(position = 7)
    private Integer CountPrep;


    public Integer getCountPrep(){return CountPrep;}
    public Integer getPreparation_ID(){return Preparation_ID;}
    public Integer getFkPreparationToDoctor(){return FkPreparationToDoctor;}
    public String getNamePrep(){return NamePrep;}
    public Date getDateEnd(){return DateEnd;}
    public double getDosage(){return  Dosage;}
    public StatusVisitPreparation getStatusVisitPreparation(){return statusVisitPreparation;}
    public String getAboutPrep(){return AboutPrep;}


    public void setCountPrep(Integer CountPrep){this.CountPrep = CountPrep;}
    public void setPreparation_ID(Integer Preparation_ID){this.Preparation_ID = Preparation_ID;}
    public void setFkPreparationToDoctor(Integer FkPreparationToDoctor){this.FkPreparationToDoctor = FkPreparationToDoctor;}
    public void setNamePrep(String NamePrep){this.NamePrep = NamePrep;}
    public void setDateEnd(Date DateEnd){this.DateEnd = DateEnd;}
    public void setDosage(double Dosage){this.Dosage = Dosage;}
    public void setStatusVisitPreparation(StatusVisitPreparation statusVisitPreparation){
        this.statusVisitPreparation = statusVisitPreparation;}
    public void setAboutPrep(String AboutPrep){this.AboutPrep = AboutPrep;}

    @Override
    public String toString() {
        return "Preparation{ Preparation_ID = " +Preparation_ID+
                ",NamePrep = " +
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
