package ru.sfedu.assessmentHealth.model;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;

public class Schedule {
    private Integer Schedule_ID;
    private Integer FkToDoctor;
    private DateWeek dateWeek;
    private Data DateSchedule;
    private Data TimeBegin;
    private Data TimeEnd;
    private StatusSchedule statusSchedule;


    public Integer getFkToDoctor(){return FkToDoctor;}
    public Integer getSchedule(){return Schedule_ID;}
    public DateWeek getDateWeek(){return dateWeek;}
    public Data getDateSchedule(){return DateSchedule;}
    public Data getTimeBegin(){return TimeBegin;}
    public Data getTimeEnd(){return TimeEnd;}
    public StatusSchedule getStatusSchedule(){return statusSchedule;}


    public void setFkToDoctor(Integer FkToDoctor){this.FkToDoctor = FkToDoctor;}
    public void setSchedule(Integer Schedule_ID){this.Schedule_ID = Schedule_ID;}
    public void setDateWeek(DateWeek dateWeek){this.dateWeek = dateWeek;}
    public void setDateSchedule(Data DateSchedule){this.DateSchedule = DateSchedule;}
    public void setTimeBegin(Data TimeBegin){this.TimeBegin = TimeBegin;}
    public void setTimeEnd(Data TimeEnd){this.TimeEnd = TimeEnd;}
    public void setStatusSchedule(StatusSchedule statusSchedule){this.statusSchedule = statusSchedule;}

    @Override
    public String toString() {
        return "Schedule{dateWeek = " + dateWeek +
                ",DateSchedule = " + DateSchedule +
                ",TimeBegin = " + TimeBegin +
                ",TimeEnd = " + TimeEnd +
                ",statusSchedule = " + statusSchedule +
                "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule schedule)) return false;
        return Objects.equals(Schedule_ID, schedule.Schedule_ID) && dateWeek == schedule.dateWeek && Objects.equals(DateSchedule, schedule.DateSchedule) && Objects.equals(TimeBegin, schedule.TimeBegin) && Objects.equals(TimeEnd, schedule.TimeEnd) && statusSchedule == schedule.statusSchedule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Schedule_ID, dateWeek, DateSchedule, TimeBegin, TimeEnd, statusSchedule);
    }
}
