package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.assessmentHealth.model.BaseId;
import ru.sfedu.assessmentHealth.model.DateWeek;
import ru.sfedu.assessmentHealth.model.StatusSchedule;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Root
public class Schedule extends BaseId {
    @Element
    @CsvBindByPosition(position = 1)
    protected ru.sfedu.assessmentHealth.model.DateWeek Week;
    @Element
    @CsvBindByPosition(position = 2)
    protected Date DateSchedule;
    @Element
    @CsvBindByPosition(position = 3)
    protected Time TimeBegin;
    @Element
    @CsvBindByPosition(position = 4)
    protected Time TimeEnd;
    @Element
    @CsvBindByPosition(position = 5)
    protected ru.sfedu.assessmentHealth.model.StatusSchedule StatSchedule;


    public ru.sfedu.assessmentHealth.model.DateWeek getWeek() {
        return Week;
    }

    public Schedule setWeek(DateWeek week) {
        Week = week;
        return this;
    }

    public Date getDateSchedule() {
        return DateSchedule;
    }

    public Schedule setDateSchedule(String dateSchedule) {DateSchedule = Date.valueOf(dateSchedule);return this;}

    public Time getTimeBegin() {
        return TimeBegin;
    }

    public Schedule setTimeBegin(String timeBegin) {
        TimeBegin = Time.valueOf(timeBegin);
        return this;
    }

    public Time getTimeEnd() {
        return TimeEnd;
    }

    public Schedule setTimeEnd(String timeEnd) {TimeEnd = Time.valueOf(timeEnd);
        return this;}

    public ru.sfedu.assessmentHealth.model.StatusSchedule getStatSchedule() {
        return StatSchedule;
    }

    public Schedule setStatSchedule(StatusSchedule statSchedule) {
        StatSchedule = statSchedule;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule schedule)) return false;
        return Week == schedule.Week && Objects.equals(DateSchedule, schedule.DateSchedule) && Objects.equals(TimeBegin, schedule.TimeBegin) && Objects.equals(TimeEnd, schedule.TimeEnd) && StatSchedule == schedule.StatSchedule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Week, DateSchedule, TimeBegin, TimeEnd, StatSchedule);
    }

    @Override
    public String toString() {
        return "{" +
                " id=" + id +
                ", Week=" + Week +
                ", DateSchedule=" + DateSchedule +
                ", TimeBegin=" + TimeBegin +
                ", TimeEnd=" + TimeEnd +
                ", StatSchedule=" + StatSchedule +

                '}';
    }
}
