package ru.sfedu.assessmentHealth.lab5.OneToOne.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Doctor extends Person {
    @Column
    protected Integer experience;
    @Column
    protected Double avgPatient;
    @Column
    protected String qualification;
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<String> specialization;

    @OneToMany(mappedBy = "preparation",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    protected Preparation linkPreparation;
    @OneToMany(mappedBy = "schedule",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    protected Schedule linkSchedule;

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



}
