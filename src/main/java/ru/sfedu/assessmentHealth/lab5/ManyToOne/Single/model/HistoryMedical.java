package ru.sfedu.assessmentHealth.lab5.ManyToOne.Single.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.Objects;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class HistoryMedical {
    @Id
    @Column
    protected Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_ID",nullable = true )
    protected Patient patient;
    @Column
    protected Date dateHistory;
    @Column
    protected String FIODoctor;
    @Column
    protected String nameDiagnosis;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        HistoryMedical that = (HistoryMedical) object;
        return Objects.equals(dateHistory, that.dateHistory) && Objects.equals(FIODoctor, that.FIODoctor) && Objects.equals(nameDiagnosis, that.nameDiagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateHistory, FIODoctor, nameDiagnosis);
    }
}
