package ru.sfedu.assessmentHealth.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.sql.Date;
import java.util.Objects;

@Root
public class Preparation extends BaseId{
    @Element
    @CsvBindByPosition(position = 1)
    protected String NamePrep;
    @Element
    @CsvBindByPosition(position = 2)
    @CsvDate("yyyy-MM-dd")
    protected Date DateEnd;
    @Element
    @CsvBindByPosition(position = 3)
    protected Double Dosage;
    @Element
    @CsvBindByPosition(position = 4)
    protected  StatusPreparation StatusVisitPreparation;
    @Element
    @CsvBindByPosition(position = 5)
    protected String AboutPrep;


    public String getNamePrep() {
        return NamePrep;
    }

    public Preparation setNamePrep(String namePrep) {
        NamePrep = namePrep;
        return this;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public Preparation setDateEnd(String dateEnd) {
        DateEnd = Date.valueOf(dateEnd);
        return this;
    }

    public Double getDosage() {
        return Dosage;
    }

    public Preparation setDosage(Double dosage) {
        Dosage = dosage;
        return this;
    }

    public StatusPreparation getStatusVisitPreparation() {
        return StatusVisitPreparation;
    }

    public Preparation setStatusVisitPreparation(StatusPreparation statusVisitPreparation) {
        StatusVisitPreparation = statusVisitPreparation;
        return this;
    }

    public String getAboutPrep() {
        return AboutPrep;
    }

    public Preparation setAboutPrep(String aboutPrep) {
        AboutPrep = aboutPrep;
        return this;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Preparation that)) return false;
        return Objects.equals(NamePrep, that.NamePrep) && Objects.equals(DateEnd, that.DateEnd) && Objects.equals(Dosage, that.Dosage) && StatusVisitPreparation == that.StatusVisitPreparation && Objects.equals(AboutPrep, that.AboutPrep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NamePrep, DateEnd, Dosage, StatusVisitPreparation, AboutPrep);
    }

    @Override
    public String toString() {
        return "{" +
                " id=" + id +
                ", NamePrep='" + NamePrep + '\'' +
                ", DateEnd=" + DateEnd +
                ", Dosage=" + Dosage +
                ", StatusVisitPreparation=" + StatusVisitPreparation +
                ", AboutPrep='" + AboutPrep + '\'' +

                '}';
    }
}
