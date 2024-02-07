package ru.sfedu.assessmentHealth.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.api.BaseTest;
import ru.sfedu.assessmentHealth.model.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class CreateObjTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(CreateObjTest.class.getName());
    @Test
    void createSchedule() {
        log.debug("createSchedule: [1] start working");
       Schedule expected = CreateObj.createSchedule(Const.TEST_SCHEDULE_DATA_WEEK
                ,Const.TEST_SCHEDULE_DATA_DATE
                ,Const.TEST_SCHEDULE_DATA_BEGIN
                ,Const.TEST_SCHEDULE_DATA_END
                ,Const.TEST_SCHEDULE_DATA_STATUS);
        expected.setId(Integer.valueOf(Const.TEST_SCHEDULE_DATA_ID));

        assertEquals(getSchedule(),expected);
        log.debug("createSchedule: [2] end ");
    }

    @Test
    void createPreparation() {
        log.debug("createPreparation: [1] start working");
        Preparation expected = CreateObj.createPreparation(
                Const.TEST_PREPARATION_DATA_NAME
                ,Const.TEST_PREPARATION_DATA_DATE_END
                ,Const.TEST_PREPARATION_DATA_DOSAGE
                ,Const.TEST_PREPARATION_DATA_VISIT
                ,Const.TEST_PREPARATION_DATA_ABOUT);
        expected.setId(Integer.valueOf(Const.TEST_PREPARATION_DATA_ID));

        assertEquals(getPreparation(),expected);
        log.debug("createPreparation: [2] end ");
    }

    @Test
    void createPatient() {
        log.debug("createPatient: [1] start working");
        Patient expected = CreateObj.createPatient(
                Const.TEST_PATIENT_DATA_FIO
                ,Const.TEST_PATIENT_DATA_AGE
                ,Const.TEST_PATIENT_DATA_GENDER
                ,Const.TEST_PATIENT_DATA_STATUS_PERSON
                ,Const.TEST_PATIENT_DATA_CELLS_BLOOD
                ,Const.TEST_PATIENT_DATA_HEMOGLOBIN
                ,Const.TEST_PATIENT_DATA_PLATELETS
                ,Const.TEST_PATIENT_DATA_GLUCOSE
                ,Const.TEST_PATIENT_DATA_CHOLESTEROL
                ,Const.TEST_PATIENT_DATA_STATUS_PATIENT
        );
        expected.setId(Integer.valueOf(Const.TEST_PATIENT_DATA_ID));

        assertEquals(getPatient(),expected);
        log.debug("createPatient: [2] end ");
    }

    @Test
    void createDoctor() {
        log.debug("createDoctor: [1] start working");
        Doctor expected = CreateObj.createDoctor(
                Const.TEST_DOCTOR_DATA_FIO
                ,Const.TEST_DOCTOR_DATA_AGE
                ,Const.TEST_DOCTOR_DATA_GENDER
                ,Const.TEST_DOCTOR_DATA_STATUS_PERSON
                ,Const.TEST_DOCTOR_DATA_EXP
                ,Const.TEST_DOCTOR_DATA_AVG_PATIENT
                ,Const.TEST_DOCTOR_DATA_QUALIFICATION
                ,Const.TEST_DOCTOR_DATA_SPECIALIZATION
        );
        expected.setId(Integer.valueOf(Const.TEST_PATIENT_DATA_ID));

        assertEquals(getDoctor().getFio(),expected.getFio());
        assertEquals(getDoctor().getAvgPatient(),expected.getAvgPatient());

        log.debug("createDoctor: [2] end ");
    }

    @Test
    void createCalcReport() {
        log.debug("createCalcReport: [1] start working");
        CalcReport expected = CreateObj.createCalcReport(
                Const.TEST_CALC_REPORT_DATA_FIO_PATIENT
                ,Const.TEST_CALC_REPORT_DATA_FIO_DOCTOR
                ,Const.TEST_CALC_REPORT_DATA_BLOOD_ANALYSIS
                ,Const.TEST_CALC_REPORT_DATA_GLUCOSE_ANALYSIS
                ,Const.TEST_CALC_REPORT_DATA_ARTERIAL_ANALYSIS
        );
        expected.setPrice(Double.valueOf(Const.TEST_CALC_REPORT_DATA_PRICE));
        expected.setId(Integer.valueOf(Const.TEST_PATIENT_DATA_ID));

        assertEquals(getCalcReport().getPrice(),expected.getPrice());
        assertEquals(getCalcReport().getFioDoctor(),expected.getFioDoctor());
        assertEquals(getCalcReport().getFioPatient(),expected.getFioPatient());
        log.debug("createCalcReport: [2] end ");
    }


    @Test
    void createScheduleNegativity() {
        log.debug("createScheduleNegativity: [1] start working");
        Schedule expected = CreateObj.createSchedule(Const.TEST_SCHEDULE_DATA_WEEK
                ,Const.TEST_SCHEDULE_DATA_DATE
                ,Const.TEST_SCHEDULE_DATA_BEGIN
                ,Const.TEST_SCHEDULE_DATA_END
                ,Const.TEST_SCHEDULE_DATA_STATUS);
        expected.setId(Integer.valueOf(Const.TEST_SCHEDULE_DATA_ID));

        assertNotEquals(null,expected.getWeek());
        assertNotEquals(null,expected.getDateSchedule());
        log.debug("createScheduleNegativity: [2] end ");
    }

    @Test
    void createPreparationNegativity() {
        log.debug("createPreparationNegativity: [1] start working");
        Preparation expected = CreateObj.createPreparation(
                Const.TEST_PREPARATION_DATA_NAME
                ,Const.TEST_PREPARATION_DATA_DATE_END
                ,Const.TEST_PREPARATION_DATA_DOSAGE
                ,Const.TEST_PREPARATION_DATA_VISIT
                ,Const.TEST_PREPARATION_DATA_ABOUT);
        expected.setId(Integer.valueOf(Const.TEST_PREPARATION_DATA_ID));

        assertNotEquals(null,expected.getDosage());
        assertNotEquals(null,expected.getAboutPrep());
        log.debug("createPreparationNegativity: [2] end ");
    }

    @Test
    void createPatientNegativity() {
        log.debug("createPatientNegativity: [1] start working");
        Patient expected = CreateObj.createPatient(
                Const.TEST_PATIENT_DATA_FIO
                ,Const.TEST_PATIENT_DATA_AGE
                ,Const.TEST_PATIENT_DATA_GENDER
                ,Const.TEST_PATIENT_DATA_STATUS_PERSON
                ,Const.TEST_PATIENT_DATA_CELLS_BLOOD
                ,Const.TEST_PATIENT_DATA_HEMOGLOBIN
                ,Const.TEST_PATIENT_DATA_PLATELETS
                ,Const.TEST_PATIENT_DATA_GLUCOSE
                ,Const.TEST_PATIENT_DATA_CHOLESTEROL
                ,Const.TEST_PATIENT_DATA_STATUS_PATIENT
        );
        expected.setId(Integer.valueOf(Const.TEST_PATIENT_DATA_ID));

        assertNotEquals(null,expected.getFio());
        assertNotEquals(null,expected.getStatusVisit());
        log.debug("createPatientNegativity: [2] end ");
    }

    @Test
    void createDoctorNegativity() {
        log.debug("createDoctorNegativity: [1] start working");
        Doctor expected = CreateObj.createDoctor(
                Const.TEST_DOCTOR_DATA_FIO
                ,Const.TEST_DOCTOR_DATA_AGE
                ,Const.TEST_DOCTOR_DATA_GENDER
                ,Const.TEST_DOCTOR_DATA_STATUS_PERSON
                ,Const.TEST_DOCTOR_DATA_EXP
                ,Const.TEST_DOCTOR_DATA_AVG_PATIENT
                ,Const.TEST_DOCTOR_DATA_QUALIFICATION
                ,Const.TEST_DOCTOR_DATA_SPECIALIZATION
        );
        expected.setId(Integer.valueOf(Const.TEST_PATIENT_DATA_ID));

        assertNotEquals(null,expected.getFio());
        assertNotEquals(null,expected.getGender());
        log.debug("createDoctorNegativity: [2] end ");
    }

    @Test
    void createCalcReportNegativity() {
        log.debug("createCalcReportNegativity: [1] start working");
        CalcReport expected = CreateObj.createCalcReport(
                Const.TEST_CALC_REPORT_DATA_FIO_PATIENT
                ,Const.TEST_CALC_REPORT_DATA_FIO_DOCTOR
                ,Const.TEST_CALC_REPORT_DATA_BLOOD_ANALYSIS
                ,Const.TEST_CALC_REPORT_DATA_GLUCOSE_ANALYSIS
                ,Const.TEST_CALC_REPORT_DATA_ARTERIAL_ANALYSIS
        );
        expected.setPrice(Double.valueOf(Const.TEST_CALC_REPORT_DATA_PRICE));
        expected.setId(Integer.valueOf(Const.TEST_PATIENT_DATA_ID));

        assertEquals(getCalcReport().getPrice(),expected.getPrice());
        assertEquals(getCalcReport().getFioDoctor(),expected.getFioDoctor());
        assertEquals(getCalcReport().getFioPatient(),expected.getFioPatient());
        log.debug("createCalcReportNegativity: [2] end ");
    }

}