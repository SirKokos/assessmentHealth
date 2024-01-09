package ru.sfedu.assessmentHealth.model;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.assessmentHealth.utils.ConvertCsvDoctor;
import ru.sfedu.assessmentHealth.utils.ConvertCsvPatient;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Root
public class CalcReport extends BaseId {

    @Element
    @CsvBindByPosition(position = 1)
    protected String fioPatient;
    @Element
    @CsvBindByPosition(position = 2)
    protected String fioDoctor;
    @Element
    @CsvBindByPosition(position = 3)
    protected Boolean bloodAnalysis;
    @Element
    @CsvBindByPosition(position = 4)
    protected Boolean glucoseAnalysis;
    @Element
    @CsvBindByPosition(position = 5)
    protected Boolean arterialAnalysis;
    @ElementList
    @CsvBindByPosition(position = 6)
    @CsvBindAndSplitByPosition(elementType = Patient.class,converter = ConvertCsvPatient.class,splitOn = "\\s",position = 6)
    protected List<Patient> patient = new ArrayList<>(1);
    @ElementList
    @CsvBindByPosition(position = 7)
    @CsvBindAndSplitByPosition(elementType = Doctor.class,converter = ConvertCsvDoctor.class,splitOn = "\\s",position = 7)
    protected List<Doctor> doctor = new ArrayList<>(1);
    @Element
    @CsvBindByPosition(position = 8)
    protected Double price;


    public String getFioPatient() {
        return fioPatient;
    }

    public CalcReport setFioPatient(String fioPatient) {
        this.fioPatient = fioPatient;
        return this;
    }

    public String getFioDoctor() {
        return fioDoctor;
    }

    public CalcReport setFioDoctor(String fio_doctor) {
        this.fioDoctor = fio_doctor;
        return this;
    }

    public Boolean getBloodAnalysis() {
        return bloodAnalysis;
    }

    public CalcReport setBloodAnalysis(Boolean bloodAnalysis) {
        this.bloodAnalysis = bloodAnalysis;
        return this;
    }

    public Boolean getGlucoseAnalysis() {
        return glucoseAnalysis;
    }

    public CalcReport setGlucoseAnalysis(Boolean glucoseAnalysis) {
        this.glucoseAnalysis = glucoseAnalysis;
        return this;
    }

    public Boolean getArterialAnalysis() {
        return arterialAnalysis;
    }

    public CalcReport setArterialAnalysis(Boolean arterialAnalysis) {
        this.arterialAnalysis = arterialAnalysis;
        return this;
    }

    public List<Patient> getPatient() {
        return patient;
    }

    public CalcReport setPatient( List<Patient> patient) {
        this.patient = patient;
        return this;
    }

    public List<Doctor> getDoctor() {
        return doctor;
    }

    public CalcReport setDoctor(List<Doctor> doctor) {
        this.doctor = doctor;
        return this;
    }


    public Double getPrice() {
        return price;
    }

    public CalcReport setPrice(Double price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalcReport that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(fioPatient, that.fioPatient) && Objects.equals(fioDoctor, that.fioDoctor) && Objects.equals(bloodAnalysis, that.bloodAnalysis) && Objects.equals(glucoseAnalysis, that.glucoseAnalysis) && Objects.equals(arterialAnalysis, that.arterialAnalysis) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fioPatient, fioDoctor, bloodAnalysis, glucoseAnalysis, arterialAnalysis, patient, doctor, price);
    }

    @Override
    public String toString() {
        return "{" +
                " id=" + id +
                ", fioPatient='" + fioPatient + '\'' +
                ", fioDoctor='" + fioDoctor + '\'' +
                ", bloodAnalysis=" + bloodAnalysis +
                ", glucoseAnalysis=" + glucoseAnalysis +
                ", arterialAnalysis=" + arterialAnalysis +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", price=" + price +
                '}';
    }
}
