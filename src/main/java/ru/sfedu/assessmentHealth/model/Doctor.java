package ru.sfedu.assessmentHealth.model;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.assessmentHealth.utils.ConvertCsvPreparation;
import ru.sfedu.assessmentHealth.utils.ConvertCsvSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Root
public class Doctor extends Person{
    @Element
    @CsvBindByPosition(position = 5)
    protected Integer experience;
    @Element
    @CsvBindByPosition(position = 6)
    protected Double avgPatient;
    @Element
    @CsvBindByPosition(position = 7)
    protected String qualification;
    @Element
    @CsvBindByPosition(position = 8)
    protected  String specialization;

    @ElementList
    @CsvBindByPosition(position = 9)
    @CsvBindAndSplitByPosition(elementType = Preparation.class,converter = ConvertCsvPreparation.class,splitOn = "\\s",position = 9)
    protected List<Preparation> linkPreparation = new ArrayList<>();
    @ElementList
    @CsvBindByPosition(position = 10)
    @CsvBindAndSplitByPosition(elementType = Schedule.class,converter = ConvertCsvSchedule.class,splitOn = "\\s",position = 10)
    protected List<Schedule> linkSchedule = new ArrayList<>();

    public Integer getExperience() {
        return experience;
    }

    public Doctor setExperience(Integer experience) {
        this.experience = experience;
        return this;
    }

    public Double getAvgPatient() {
        return avgPatient;
    }

    public Doctor setAvgPatient(Double avgPatient) {
        this.avgPatient = avgPatient;
        return this;

    }

    public String getQualification() {
        return qualification;
    }

    public Doctor setQualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Doctor setSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }

    public List<Preparation> getLinkPreparation() {
        return linkPreparation;
    }

    public Doctor setLinkPreparation(List<Preparation> linkPreparation) {
        this.linkPreparation = linkPreparation;
        return this;
    }

    public List<Schedule> getLinkSchedule() {
        return linkSchedule;
    }

    public Doctor setLinkSchedule(List<Schedule> linkSchedule) {
        this.linkSchedule = linkSchedule;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor doctor)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(experience, doctor.experience) && Objects.equals(avgPatient, doctor.avgPatient) && Objects.equals(qualification, doctor.qualification) && Objects.equals(specialization, doctor.specialization) && Objects.equals(linkPreparation, doctor.linkPreparation) && Objects.equals(linkSchedule, doctor.linkSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience, avgPatient, qualification, specialization, linkPreparation, linkSchedule);
    }

    @Override
    public String toString() {
        return "{" +
                " id=" + id +
                ", experience=" + experience +
                ", avgPatient=" + avgPatient +
                ", qualification='" + qualification + '\'' +
                ", specialization='" + specialization + '\'' +
                ", linkPreparation=" + linkPreparation +
                ", linkSchedule=" + linkSchedule +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", Gender='" + Gender + '\'' +
                ", statusPerson=" + statusPerson +

                '}';
    }
}
