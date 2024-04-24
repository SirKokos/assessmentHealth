package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.api;


import lombok.extern.slf4j.Slf4j;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.lab1.utils.HibernateUtil;
import ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model.*;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

@Slf4j
public class BaseTestLab3 {
    HibernateDataProviderLab3 hibernateDataProviderLab3 = new HibernateDataProviderLab3();
    static {
        PropertyConfig.setConfigPath(Const.NAME_PROPERTY_FILE);
        HibernateUtil.setPathConfig(PropertyConfig.getPropertyValue(Const.LAB3_HBN_CFG,Const.NAME_PROPERTY_FILE));
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

        doctor.setId(Integer.valueOf(Const.TEST_DOCTOR_DATA_ID));
        doctor.setFio(Const.TEST_DOCTOR_DATA_FIO);
        doctor.setAge(Integer.valueOf(Const.TEST_DOCTOR_DATA_AGE));
        doctor.setGender(Const.TEST_DOCTOR_DATA_GENDER);
        doctor.setStatusPerson(StatusPerson.valueOf(Const.TEST_DOCTOR_DATA_STATUS_PERSON));

        doctor.setExperience(Integer.valueOf(Const.TEST_DOCTOR_DATA_EXP));
        doctor.setAvgPatient(Double.valueOf(Const.TEST_DOCTOR_DATA_AVG_PATIENT));
        doctor.setQualification(Const.TEST_DOCTOR_DATA_QUALIFICATION);
        doctor.setSpecialization(Const.TEST_DOCTOR_DATA_SPECIALIZATION);

//
//        List<Schedule> s = new ArrayList<>();
//        s.add(getSchedule());
//        s.add(getSchedule());
//
//        List<Preparation> p = new ArrayList<>();
//        p.add(getPreparation());
//        p.add(getPreparation());
//
//        doctor.setLinkSchedule(s);
//        doctor.setLinkPreparation(p);
        return doctor;
    }

}
