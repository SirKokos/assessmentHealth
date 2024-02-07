package ru.sfedu.assessmentHealth.api;

import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    {
        PropertyConfig.setConfigPath(Const.NAME_PROPERTY_FILE);
    }

    protected CalcReport getCalcReport(){
        CalcReport calcReport = new CalcReport();
        calcReport.setId(Integer.valueOf(Const.TEST_CALC_REPORT_DATA_ID));
        calcReport.setFioPatient(Const.TEST_CALC_REPORT_DATA_FIO_PATIENT)
                .setFioDoctor(Const.TEST_CALC_REPORT_DATA_FIO_DOCTOR)
                .setBloodAnalysis(Boolean.valueOf(Const.TEST_CALC_REPORT_DATA_BLOOD_ANALYSIS))
                .setGlucoseAnalysis(Boolean.valueOf(Const.TEST_CALC_REPORT_DATA_GLUCOSE_ANALYSIS))
                .setArterialAnalysis(Boolean.valueOf(Const.TEST_CALC_REPORT_DATA_ARTERIAL_ANALYSIS))
                .setPatient(List.of(getPatient()))
                .setDoctor(List.of(getDoctor()));
        calcReport.setPrice(Double.valueOf(Const.TEST_CALC_REPORT_DATA_PRICE));
        return calcReport;
    }

   protected Doctor getDoctor(){
       Doctor doctor = new Doctor();

       doctor.setId(Integer.valueOf(Const.TEST_DOCTOR_DATA_ID));

       doctor.setFio(Const.TEST_DOCTOR_DATA_FIO)
               .setAge(Integer.valueOf(Const.TEST_DOCTOR_DATA_AGE))
               .setGender(Const.TEST_DOCTOR_DATA_GENDER)
               .setStatusPerson(StatusPerson.valueOf(Const.TEST_DOCTOR_DATA_STATUS_PERSON));

       doctor.setExperience(Integer.valueOf(Const.TEST_DOCTOR_DATA_EXP))
               .setAvgPatient(Double.valueOf(Const.TEST_DOCTOR_DATA_AVG_PATIENT))
               .setQualification(Const.TEST_DOCTOR_DATA_QUALIFICATION)
               .setSpecialization(Const.TEST_DOCTOR_DATA_SPECIALIZATION);


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
       patient.setId(Integer.valueOf(Const.TEST_PATIENT_DATA_ID));

       patient.setFio(Const.TEST_PATIENT_DATA_FIO)
               .setAge(Integer.valueOf(Const.TEST_PATIENT_DATA_AGE))
               .setGender(Const.TEST_PATIENT_DATA_GENDER)
               .setStatusPerson(StatusPerson.valueOf(Const.TEST_PATIENT_DATA_STATUS_PERSON));

       patient.setCellsBlood(Double.valueOf(Const.TEST_PATIENT_DATA_CELLS_BLOOD))
               .setHemoglobin(Double.valueOf(Const.TEST_PATIENT_DATA_HEMOGLOBIN))
               .setPlatelets(Double.valueOf(Const.TEST_PATIENT_DATA_PLATELETS))
               .setGlucose(Double.valueOf(Const.TEST_PATIENT_DATA_GLUCOSE))
               .setCholesterol(Double.valueOf(Const.TEST_PATIENT_DATA_CHOLESTEROL))
               .setStatusVisit(StatusPatient.valueOf(Const.TEST_PATIENT_DATA_STATUS_PATIENT));
       return patient;
   }


   protected Preparation getPreparation(){
       Preparation preparation = new Preparation();

       preparation.setId(Integer.valueOf(Const.TEST_PREPARATION_DATA_ID));

       preparation.setNamePrep(Const.TEST_PREPARATION_DATA_NAME)
               .setDateEnd(Const.TEST_PREPARATION_DATA_DATE_END)
               .setDosage(Double.valueOf(Const.TEST_PREPARATION_DATA_DOSAGE))
               .setStatusVisitPreparation(StatusPreparation.valueOf(Const.TEST_PREPARATION_DATA_VISIT))
               .setAboutPrep(Const.TEST_PREPARATION_DATA_ABOUT);

       return preparation;
   }


   protected Schedule getSchedule(){
       Schedule schedule = new Schedule();
       schedule.setId(Integer.valueOf(Const.TEST_SCHEDULE_DATA_ID));

       schedule.setWeek(DateWeek.valueOf(Const.TEST_SCHEDULE_DATA_WEEK))
               .setDateSchedule(Const.TEST_SCHEDULE_DATA_DATE)
               .setTimeBegin(Const.TEST_SCHEDULE_DATA_BEGIN)
               .setTimeEnd(Const.TEST_SCHEDULE_DATA_END)
               .setStatSchedule(StatusSchedule.valueOf(Const.TEST_SCHEDULE_DATA_STATUS));

       return schedule;
   }
}
