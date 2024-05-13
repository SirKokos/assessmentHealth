package ru.sfedu.assessmentHealth.lab5.ManyToMany.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class CalcReport{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer Id;
    @Column
    protected String fioPatient;
    @Column
    protected String fioDoctor;
    @Column
    protected Boolean bloodAnalysis;
    @Column
    protected Boolean glucoseAnalysis;
    @Column
    protected Boolean arterialAnalysis;
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "ID_DOCTOR")
    @Column(name = "ID_PATIENT")
    protected Map<Integer,Integer>  doctorPatientMapId = new HashMap<>();
    @Column
    protected Double price;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CalcReport that = (CalcReport) object;
        return Objects.equals(Id, that.Id) && Objects.equals(fioPatient, that.fioPatient) && Objects.equals(fioDoctor, that.fioDoctor) && Objects.equals(bloodAnalysis, that.bloodAnalysis) && Objects.equals(glucoseAnalysis, that.glucoseAnalysis) && Objects.equals(arterialAnalysis, that.arterialAnalysis) && Objects.equals(doctorPatientMapId, that.doctorPatientMapId) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, fioPatient, fioDoctor, bloodAnalysis, glucoseAnalysis, arterialAnalysis, doctorPatientMapId, price);
    }
}
