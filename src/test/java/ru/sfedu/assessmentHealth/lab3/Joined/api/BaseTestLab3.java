package ru.sfedu.assessmentHealth.lab3.Joined.api;


import lombok.extern.slf4j.Slf4j;
import ru.sfedu.assessmentHealth.Const;

import ru.sfedu.assessmentHealth.lab3.Joined.model.*;
import ru.sfedu.assessmentHealth.lab3.Joined.utils.HibernateUtil;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

@Slf4j
public class BaseTestLab3 {

     HibernateDataProviderLab3 hibernateDataProviderLab3 = new HibernateDataProviderLab3();
    static {
        PropertyConfig.setConfigPath(Const.NAME_PROPERTY_FILE);
        HibernateUtil.setPathConfig(PropertyConfig.getPropertyValue(Const.LAB3_HBN_CFG_JOINED,Const.NAME_PROPERTY_FILE));
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


        return patient;
    }
    public Doctor getDoctor(){
        Doctor doctor = new Doctor();

        doctor.setId(1);
        doctor.setFio(Const.TEST_DOCTOR_DATA_FIO);
        doctor.setAge(25);
        doctor.setGender(Const.TEST_DOCTOR_DATA_GENDER);
        doctor.setStatusPerson(StatusPerson.valueOf(Const.TEST_DOCTOR_DATA_STATUS_PERSON));

        doctor.setExperience(12);
        doctor.setAvgPatient(4.0);
        doctor.setQualification(Const.TEST_DOCTOR_DATA_QUALIFICATION);
        doctor.setSpecialization(Const.TEST_DOCTOR_DATA_SPECIALIZATION);

        return doctor;
    }

}
