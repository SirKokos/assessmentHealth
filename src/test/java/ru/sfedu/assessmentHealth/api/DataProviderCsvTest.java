package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DataProviderCsvTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(DataProviderCsvTest.class.getName());
    DataProviderCsv providerCSV = new DataProviderCsv();

    @Test
    @Order(1)
    void insertDoctor() {
        log.debug("insertDataDoctor [1]: - start working test ");
        Doctor d = getDoctor();
        StatusAnswer actual = providerCSV.insertDoctor(d);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertDataDoctor [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(2)
    void insertPatient() {
        log.debug("insertPatient [1]: - start working test ");
        Patient p = getPatient();
        StatusAnswer actual = providerCSV.insertPatient(p);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertPatient [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(3)
    void insertPreparation() {
        log.debug("insertPreparation [1]: - start working test ");
        Preparation p = getPreparation();
        StatusAnswer actual = providerCSV.insertPreparation(p);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertPreparation [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(4)
    void insertSchedule() {
        log.debug("insertSchedule [1]: - start working test ");
        Schedule s = getSchedule();
        StatusAnswer actual = providerCSV.insertSchedule(s);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertSchedule [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(5)
    void insertCalcReport() {
        log.debug("insertCalcReport [1]: - start working test ");
        CalcReport s = getCalcReport();
        StatusAnswer actual = providerCSV.insertCalcReport(s);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertCalcReport [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(6)
    void selectDoctorId() {
        log.debug("selectDoctorId [1]: - start working test");
        Optional<Doctor> actual =  providerCSV.selectDoctorId(1);
        assertEquals(actual.get(),getDoctor());
        log.debug("selectDoctorId [2]: - end working test ");
    }

    @Test
    @Order(7)
    void selectPatientId() {
        log.debug("selectPatientId [1]: - start working test");
        Optional<Patient> actual =  providerCSV.selectPatientId(1);
        assertEquals(actual.get(),getPatient());
        log.debug("selectPatientId [2]: - end working test");
    }

    @Test
    @Order(8)
    void selectScheduleId() {
        log.debug("selectScheduleId [1]: - start working test");
        Optional<Schedule> actual =  providerCSV.selectScheduleId(1);
        assertEquals(actual.get(),getSchedule());
        log.debug("selectScheduleId [2]: - end working test");
    }

    @Test
    @Order(9)
    void selectPreparationId() {
        log.debug("selectPreparationId [1]: - start working test");
        Optional<Preparation> actual =  providerCSV.selectPreparationId(1);
        assertEquals(actual.get(),getPreparation());
        log.debug("selectPreparationId [2]: - end working test");
    }

    @Test
    @Order(10)
    void selectCalcReport() {
        log.debug("selectCalcReport [1]: - start working test");
//        getCalcReport().setId(1);
        Optional<CalcReport> actual =  providerCSV.selectCalcReport(1);
        assertEquals(actual.get(),getCalcReport());
        log.debug("selectCalcReport [2]: - end working test {}",actual);

    }

    @Test
    @Order(11)
    void deleteDoctor() {
        log.debug("deleteDoctor [1]: - start working test");
        StatusAnswer actual =  providerCSV.deleteDoctor(1);
        assertTrue(providerCSV.selectDoctorId(1).isEmpty());
        assertEquals(actual,StatusAnswer.OK);
        log.debug("deleteDoctor [2]: - end working test");

    }

    @Test
    @Order(12)
    void selectDoctorIdNegativity() {
        log.debug("selectDoctorIdNegativity [1]: - start working test");
        Optional<Doctor> actual =  providerCSV.selectDoctorId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectDoctorIdNegativity [2]: - end working test");
    }

    @Test
    @Order(13)
    void selectPatientIdNegativity() {
        log.debug("selectPatientIdNegativity [1]: - start working test");
        Optional<Patient> actual =  providerCSV.selectPatientId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectPatientIdNegativity [2]: - end working test");
    }

    @Test
    @Order(14)
    void selectScheduleIdNegativity() {
        log.debug("selectScheduleIdNegativity [1]: - start working test");
        Optional<Schedule> actual =  providerCSV.selectScheduleId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectScheduleIdNegativity [2]: - end working test");
    }

    @Test
    @Order(15)
    void selectPreparationIdNegativity() {
        log.debug("selectPreparationIdNegativity [1]: - start working test");
        Optional<Preparation> actual =  providerCSV.selectPreparationId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectPreparationIdNegativity [2]: - end working test");
    }

    @Test
    @Order(16)
    void selectCalcReportNegativity() {
        log.debug("selectCalcReportNegativity [1]: - start working test");
        Optional<CalcReport> actual =  providerCSV.selectCalcReport(0);
        assertTrue(actual.isEmpty());
        log.debug("selectCalcReportNegativity [2]: - end working test");

    }

    @Test
    @Order(17)
    void selectAllDoctor() {
        log.debug("selectAllDoctor [1]: - start working test");
        Optional<List<Doctor>> actual =  providerCSV.selectAllDoctor();
        System.out.println(actual);
//        assertFalse(actual.isEmpty());
        log.debug("selectAllDoctor [2]: - end working test");

    }

}