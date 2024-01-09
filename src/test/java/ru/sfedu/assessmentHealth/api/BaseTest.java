package ru.sfedu.assessmentHealth.api;

import ru.sfedu.assessmentHealth.model.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected CalcReport getCalcReport(){
        CalcReport calcReport = new CalcReport();
        calcReport.setId(1);
        calcReport.setFioPatient("Bob")
                .setFioDoctor("Sim Artem Evgen")
                .setBloodAnalysis(true)
                .setGlucoseAnalysis(true)
                .setArterialAnalysis(true)
                .setPatient(List.of(getPatient()))
                .setDoctor(List.of(getDoctor()))
                .setPrice(123.31);

        return calcReport;
    }

   protected Doctor getDoctor(){
       Doctor doctor = new Doctor();

       doctor.setId(1);

       doctor.setFio("Sim Artem Evgen")
               .setAge(29)
               .setGender("M")
               .setStatusPerson(StatusPerson.DOCTOR);

       doctor.setExperience(12)
               .setAvgPatient(12.3)
               .setQualification("Med")
               .setSpecialization("Genikolog");


       List<Schedule> s = new ArrayList<>();
       s.add(getSchedule());
       s.add(getSchedule());

       List<Preparation> p = new ArrayList<>();
       p.add(getPreparation());
       p.add(getPreparation());

       doctor.setLinkSchedule(s);
       doctor.setLinkPreparation(p);

       return doctor;
   }

   protected Patient getPatient(){
       Patient patient = new Patient();
       patient.setId(1);

       patient.setFio("Bob")
               .setAge(23)
               .setGender("M")
               .setStatusPerson(StatusPerson.PATIENT);

       patient.setCellsBlood(12.3)
               .setHemoglobin(21.3)
               .setPlatelets(1.2)
               .setGlucose(11.3)
               .setCholesterol(12.3)
               .setStatusVisit(StatusPatient.IN);

       return patient;
   }


   protected Preparation getPreparation(){
       Preparation preparation = new Preparation();

       preparation.setId(1);

       preparation.setNamePrep("Ubeprofen")
               .setDateEnd("2024-12-20")
               .setDosage(2.3)
               .setStatusVisitPreparation(StatusPreparation.LOW)
               .setAboutPrep("this prep important");

       return preparation;
   }


   protected Schedule getSchedule(){
       Schedule schedule = new Schedule();
       schedule.setId(1);

       schedule.setWeek(DateWeek.SATURDAY)
               .setDateSchedule("2024-04-23")
               .setTimeBegin("9:20:30")
               .setTimeEnd("18:23:20")
               .setStatSchedule(StatusSchedule.FREE);

       return schedule;
   }
}