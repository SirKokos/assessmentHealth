package ru.sfedu.assessmentHealth.lab4.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Schedule{

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer Id;

    @Enumerated(EnumType.STRING)
    protected DateWeek Week;
    @Column
    protected Date DateSchedule;
    @Column
    protected Time TimeBegin;
    @Column
    protected Time TimeEnd;
    @Enumerated(EnumType.STRING)
    protected StatusSchedule StatSchedule;



}
