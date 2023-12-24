package ru.sfedu.assessmentHealth.utils;

import ru.sfedu.assessmentHealth.model.*;

public class TestUtil {
    public static Doctor createDoctor(){
        Doctor doctorTest = new Doctor();
        doctorTest.setName("Artem");
        doctorTest.setSurname("Sim");
        doctorTest.setSecondName("Evgeni");
        doctorTest.setAge(20);
        doctorTest.setGender('M');
        doctorTest.setExperience(3);
        doctorTest.setAvgPatient(23.5);
        doctorTest.setQualification("Доктор");
        doctorTest.setSpecialization("Genekolog");
        doctorTest.setStatus(StatusPerson.DOCTOR);

        return doctorTest;
    }

    public static Patient createPatient(){
        Patient patientTest = new Patient();
        patientTest.setName("Artem_2");
        patientTest.setSurname("Sim");
        patientTest.setSecondName("Evgeni");
        patientTest.setAge(20);
        patientTest.setGender('M');
        patientTest.setCellsBlood(1.5);
        patientTest.setHemoglobin(1.4);
        patientTest.setPlatelets(12.3);
        patientTest.setTestosterone(12.31);
        patientTest.setGlucose(12.23);
        patientTest.setCholesterol(12.23);
        patientTest.setArterialPress((short) 103);
        patientTest.setStatus(StatusPerson.PATIENT);
        patientTest.setStatusVisit(StatusVisit.IN);

        return patientTest;
    }

    public static Preparation createPreparation(){
        Preparation prep = new Preparation();
//        prep.setFkPreparationToDoctor(1);
//        prep.setFkPreparationToDoctor(2);
        prep.setNamePrep("asdasdadadsda");
        prep.setDosage(12.123);
        prep.setStatusVisitPreparation(StatusVisitPreparation.LOW);
        prep.setAboutPrep("dwdqdwdqdqdqdw");
        prep.setCountPrep(12);
        return prep;
    }

    public static Schedule createSchedule(){
        Schedule schedule = new Schedule();
        schedule.setFkToDoctor(1);
        schedule.setDateWeek(DateWeek.MONDAY);
        schedule.setStatusSchedule(StatusSchedule.PROCESS);
        return  schedule;
    }

    public static CalcReport createCalcReport(){
        CalcReport calcReport = new CalcReport();
        calcReport.setFkToDoctor(1);
        calcReport.setFkToPatient(1);
        calcReport.setNameDoctor("Artem");
        calcReport.setPatientName("Artem_2");
        calcReport.setBloodAnalyse(true);
        calcReport.setGlucoseAnalyse(true);
        calcReport.setHormoneAnalyse(true);
        calcReport.setArterialAnalyse(true);
        calcReport.setPrice(123.4);
        return calcReport;
    }

}
