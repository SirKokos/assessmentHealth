package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model;



import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Patient extends Person{
    @Column
    protected Double cellsBlood;
    @Column
    protected Double hemoglobin;
    @Column
    protected Double platelets;
    @Column
    protected Double glucose;
    @Column
    protected Double cholesterol;
    @Enumerated(EnumType.STRING)
    protected StatusVisit statusVisit;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Patient patient = (Patient) object;
        return Objects.equals(cellsBlood, patient.cellsBlood) && Objects.equals(hemoglobin, patient.hemoglobin) && Objects.equals(platelets, patient.platelets) && Objects.equals(glucose, patient.glucose) && Objects.equals(cholesterol, patient.cholesterol) && statusVisit == patient.statusVisit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cellsBlood, hemoglobin, platelets, glucose, cholesterol, statusVisit);
    }
}
