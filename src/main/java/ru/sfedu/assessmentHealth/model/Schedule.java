package ru.sfedu.assessmentHealth.model;
import javax.xml.crypto.Data;
public class Schedule {
    private DateWeek dateWeek;
    private Data DateSchedule;
    private Data TimeBegin;
    private Data TimeEnd;
    private StatusSchedule statusSchedule;

    public DateWeek getDateWeek(){return dateWeek;}
    public Data getDateSchedule(){return DateSchedule;}
    public Data getTimeBegin(){return TimeBegin;}
    public Data getTimeEnd(){return TimeEnd;}
    public StatusSchedule getStatusSchedule(){return statusSchedule;}

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


}
