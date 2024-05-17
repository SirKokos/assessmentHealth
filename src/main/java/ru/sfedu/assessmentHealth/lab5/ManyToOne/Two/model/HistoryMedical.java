package ru.sfedu.assessmentHealth.lab5.ManyToOne.Two.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "historymedical")
public class HistoryMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
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
