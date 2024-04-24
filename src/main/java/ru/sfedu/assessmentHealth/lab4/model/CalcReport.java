package ru.sfedu.assessmentHealth.lab4.model;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;


import javax.persistence.*;
import java.util.*;

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
    @ElementCollection
    @CollectionTable(name = "doctorPatientMap")
    @MapKeyColumn()
    @Column
    protected Map<Doctor,Patient>  doctorPatientMap = new HashMap<Doctor,Patient>();
    @Column
    protected Double price;


}
