package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model;



import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Doctor extends  Person{
    @Column
    protected Integer experience;
    @Column
    protected Double avgPatient;
    @Column
    protected String qualification;
    @Column
    protected  String specialization;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Doctor doctor = (Doctor) object;
        return Objects.equals(experience, doctor.experience) && Objects.equals(avgPatient, doctor.avgPatient) && Objects.equals(qualification, doctor.qualification) && Objects.equals(specialization, doctor.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience, avgPatient, qualification, specialization);
    }

//    protected List<Preparation> linkPreparation = new ArrayList<>();
//    protected List<Schedule> linkSchedule = new ArrayList<>();
}
