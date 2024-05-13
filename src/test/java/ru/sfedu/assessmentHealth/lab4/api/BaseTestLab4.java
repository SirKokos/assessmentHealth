package ru.sfedu.assessmentHealth.lab4.api;


import lombok.extern.slf4j.Slf4j;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.lab4.utils.HibernateUtil;

import ru.sfedu.assessmentHealth.lab4.model.*;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Slf4j
public class BaseTestLab4 {
     HibernateDataProviderLab4 hibernateDataProviderLab4 = new HibernateDataProviderLab4();
    static {
        PropertyConfig.setConfigPath(Const.NAME_PROPERTY_FILE);
        HibernateUtil.setPathConfig(PropertyConfig.getPropertyValue(Const.LAB4_HBN_CFG,Const.NAME_PROPERTY_FILE));
    }

    public Person getPerson(){
        log.debug("Hi this test class for lombok");
        Person person = new Person();
        person.setId(1);
        person.setAge(12);
        person.setFio("Sim Artem");
        return person;
    }
    public Patient getPatient(){
        Patient patient = new Patient();

        patient.setId(Integer.valueOf(Const.TEST_PATIENT_DATA_ID));

        patient.setFio(Const.TEST_PATIENT_DATA_FIO);
        patient .setAge(Integer.valueOf(Const.TEST_PATIENT_DATA_AGE));
        patient .setGender(Const.TEST_PATIENT_DATA_GENDER);

        patient.setStatusPerson(StatusPerson.valueOf(Const.TEST_PATIENT_DATA_STATUS_PERSON));

        patient.setCellsBlood(Double.valueOf(Const.TEST_PATIENT_DATA_CELLS_BLOOD));
        patient.setHemoglobin(Double.valueOf(Const.TEST_PATIENT_DATA_HEMOGLOBIN));
        patient.setPlatelets(Double.valueOf(Const.TEST_PATIENT_DATA_PLATELETS));
        patient.setGlucose(Double.valueOf(Const.TEST_PATIENT_DATA_GLUCOSE));
        patient.setCholesterol(Double.valueOf(Const.TEST_PATIENT_DATA_CHOLESTEROL));
        patient.setStatusVisit(StatusVisit.valueOf(Const.TEST_PATIENT_DATA_STATUS_PATIENT));
        List<String> symptoms = new ArrayList<>();
        symptoms.add(Const.TEST_PATIENT_DATA_SYMPTOM_PATIENT);
        symptoms.add(Const.TEST_PATIENT_DATA_SYMPTOM_PATIENT_2);
        patient.setSymptoms(symptoms);

        return patient;
    }
    public Doctor getDoctor(){
        Doctor doctor = new Doctor();

        doctor.setId(Integer.valueOf(Const.TEST_DOCTOR_DATA_ID));
        doctor.setFio(Const.TEST_DOCTOR_DATA_FIO);
        doctor.setAge(Integer.valueOf(Const.TEST_DOCTOR_DATA_AGE));
        doctor.setGender(Const.TEST_DOCTOR_DATA_GENDER);
        doctor.setStatusPerson(StatusPerson.valueOf(Const.TEST_DOCTOR_DATA_STATUS_PERSON));

        doctor.setExperience(Integer.valueOf(Const.TEST_DOCTOR_DATA_EXP));
        doctor.setAvgPatient(Double.valueOf(Const.TEST_DOCTOR_DATA_AVG_PATIENT));
        doctor.setQualification(Const.TEST_DOCTOR_DATA_QUALIFICATION);

        Set<String> s = new HashSet<>();
        s.add(Const.TEST_DOCTOR_DATA_SPECIALIZATION);
        s.add(Const.TEST_DOCTOR_DATA_SPECIALIZATION_2);
        doctor.setSpecialization(s);




        return doctor;
    }

    protected Preparation getPreparation(){
        Preparation preparation = new Preparation();

//        preparation.setId(Integer.valueOf(Const.TEST_PREPARATION_DATA_ID));

        preparation.setNamePrep(Const.TEST_PREPARATION_DATA_NAME);
        preparation.setDateEnd(Date.valueOf(Const.TEST_PREPARATION_DATA_DATE_END));
        preparation.setDosage(Double.valueOf(Const.TEST_PREPARATION_DATA_DOSAGE));
        preparation.setStatusVisitPreparation(StatusPreparation.valueOf(Const.TEST_PREPARATION_DATA_VISIT));
        preparation.setAboutPrep(Const.TEST_PREPARATION_DATA_ABOUT);

        return preparation;
    }


    protected Schedule getSchedule(){
        Schedule schedule = new Schedule();
//        schedule.setId(Integer.valueOf(Const.TEST_SCHEDULE_DATA_ID));

        schedule.setWeek(DateWeek.valueOf(Const.TEST_SCHEDULE_DATA_WEEK));
        schedule.setDateSchedule(Date.valueOf(Const.TEST_SCHEDULE_DATA_DATE));
        schedule.setTimeBegin(Time.valueOf(Const.TEST_SCHEDULE_DATA_BEGIN));
        schedule.setTimeEnd(Time.valueOf(Const.TEST_SCHEDULE_DATA_END));
        schedule.setStatSchedule(StatusSchedule.valueOf(Const.TEST_SCHEDULE_DATA_STATUS));

        return schedule;
    }
    protected CalcReport getCalcReport(){
        CalcReport calcReport = new CalcReport();
        calcReport.setId(Integer.valueOf(Const.TEST_CALC_REPORT_DATA_ID));
        calcReport.setFioPatient(Const.TEST_CALC_REPORT_DATA_FIO_PATIENT);
        calcReport.setFioDoctor(Const.TEST_CALC_REPORT_DATA_FIO_DOCTOR);
        calcReport.setBloodAnalysis(Boolean.valueOf(Const.TEST_CALC_REPORT_DATA_BLOOD_ANALYSIS));
        calcReport.setGlucoseAnalysis(Boolean.valueOf(Const.TEST_CALC_REPORT_DATA_GLUCOSE_ANALYSIS));
        calcReport.setArterialAnalysis(Boolean.valueOf(Const.TEST_CALC_REPORT_DATA_ARTERIAL_ANALYSIS));

        Map<Integer,Integer> doctorPatientMapId = new HashMap<>();
        doctorPatientMapId.put(getDoctor().getId(),getPatient().getId());

        calcReport.setDoctorPatientMapId(doctorPatientMapId);
        calcReport.setPrice(Double.valueOf(Const.TEST_CALC_REPORT_DATA_PRICE));
        return calcReport;
    }
}
