package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.assessmentHealth.model.Person;
import ru.sfedu.assessmentHealth.model.StatusVisitEn;

import javax.persistence.Entity;
import java.util.Objects;

@Root
@Entity(name = "Patient")
public class Patient extends Person {
    @Element
    @CsvBindByPosition(position = 5)
    protected Double cellsBlood;
    @Element
    @CsvBindByPosition(position = 6)
    protected Double hemoglobin;
    @Element
    @CsvBindByPosition(position = 7)
    protected Double platelets;
    @Element
    @CsvBindByPosition(position = 8)
    protected Double glucose;
    @Element
    @CsvBindByPosition(position = 9)
    protected Double cholesterol;
    @Element
    @CsvBindByPosition(position = 10)
    protected StatusVisitEn statusVisit;

    public Double getCellsBlood() {
        return cellsBlood;
    }

    public Patient setCellsBlood(Double cellsBlood) {
        this.cellsBlood = cellsBlood;
        return this;
    }

    public Double getHemoglobin() {
        return hemoglobin;
    }

    public Patient setHemoglobin(Double hemoglobin) {
        this.hemoglobin = hemoglobin;
        return this;
    }

    public Double getPlatelets() {
        return platelets;
    }

    public Patient setPlatelets(Double platelets) {
        this.platelets = platelets;
        return this;
    }

    public Double getGlucose() {
        return glucose;
    }

    public Patient setGlucose(Double glucose) {
        this.glucose = glucose;
        return this;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public Patient setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
        return this;
    }

    public StatusVisitEn getStatusVisit() {
        return statusVisit;
    }

    public Patient setStatusVisit(StatusVisitEn statusVisit) {
        this.statusVisit = statusVisit;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient patient)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(cellsBlood, patient.cellsBlood) && Objects.equals(hemoglobin, patient.hemoglobin) && Objects.equals(platelets, patient.platelets) && Objects.equals(glucose, patient.glucose) && Objects.equals(cholesterol, patient.cholesterol) && statusVisit == patient.statusVisit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cellsBlood, hemoglobin, platelets, glucose, cholesterol, statusVisit);
    }

    @Override
    public String toString() {
        return "{" +
                " id=" + id +
                ", cellsBlood=" + cellsBlood +
                ", hemoglobin=" + hemoglobin +
                ", platelets=" + platelets +
                ", glucose=" + glucose +
                ", cholesterol=" + cholesterol +
                ", statusVisit=" + statusVisit +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", Gender='" + Gender + '\'' +
                ", statusPerson=" + statusPat +
                '}';
    }
}
