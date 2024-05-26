package ru.sfedu.assessmentHealth.lab5.ManyToMany.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    protected Set<Patient> patients = new HashSet<Patient>();

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
