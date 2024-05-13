package ru.sfedu.assessmentHealth.lab5.OneToOne.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


//@Builder
@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Patient extends Person {
//    @OneToMany(
//            mappedBy = "insurancepolice",
//            cascade = CascadeType.PERSIST
//    )
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
        )
    @PrimaryKeyJoinColumn
    protected InsurancePolice insurancePolice;
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
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    protected List<String> symptoms;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Patient patient = (Patient) object;
        return Objects.equals(cellsBlood, patient.cellsBlood) && Objects.equals(hemoglobin, patient.hemoglobin) && Objects.equals(platelets, patient.platelets) && Objects.equals(glucose, patient.glucose) && Objects.equals(cholesterol, patient.cholesterol) && statusVisit == patient.statusVisit && Objects.equals(symptoms, patient.symptoms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cellsBlood, hemoglobin, platelets, glucose, cholesterol, statusVisit, symptoms);
    }
}
