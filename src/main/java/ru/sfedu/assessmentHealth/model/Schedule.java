package ru.sfedu.assessmentHealth.model;
import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Root
public class Schedule {
    @Element
    @CsvBindByPosition(position = 0)
    private Integer Schedule_ID;
    @Element
    @CsvBindByPosition(position = 1)
    private Integer FkToDoctor;
    @Element
    @CsvBindByPosition(position = 2)
    private DateWeek dateWeek;
    @Element
    @CsvBindByPosition(position = 3)
    private Date DateSchedule;
    @Element
    @CsvBindByPosition(position = 4)
    private Date TimeBegin;
    @Element
    @CsvBindByPosition(position = 5)
    private Date TimeEnd;
    @Element
    @CsvBindByPosition(position = 6)
    private StatusSchedule statusSchedule;


    public Integer getFkToDoctor(){return FkToDoctor;}
    public Integer getScheduleID(){return Schedule_ID;}
    public DateWeek getDateWeek(){return dateWeek;}
    public Date getDateSchedule(){return DateSchedule;}
    public Date getTimeBegin(){return TimeBegin;}
    public Date getTimeEnd(){return TimeEnd;}
    public StatusSchedule getStatusSchedule(){return statusSchedule;}


    public void setFkToDoctor(Integer FkToDoctor){this.FkToDoctor = FkToDoctor;}
    public void setScheduleID(Integer Schedule_ID){this.Schedule_ID = Schedule_ID;}
    public void setDateWeek(DateWeek dateWeek){this.dateWeek = dateWeek;}
    public void setDateSchedule(Date DateSchedule){this.DateSchedule = DateSchedule;}
    public void setTimeBegin(Date TimeBegin){this.TimeBegin = TimeBegin;}
    public void setTimeEnd(Date TimeEnd){this.TimeEnd = TimeEnd;}
    public void setStatusSchedule(StatusSchedule statusSchedule){this.statusSchedule = statusSchedule;}

    @Override
    public String toString() {
        return "Schedule{ Schedule_ID = " + Schedule_ID+
                ",dateWeek = " + dateWeek +
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
