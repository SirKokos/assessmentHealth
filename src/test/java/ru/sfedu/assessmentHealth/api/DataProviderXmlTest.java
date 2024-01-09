package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.sfedu.assessmentHealth.model.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DataProviderXmlTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(DataProviderXmlTest.class.getName());
    DataProviderXml providerXML = new DataProviderXml();

    @Test
    @Order(1)
    void insertDoctor() {
        log.debug("insertDataDoctor [1]: - start working test ");
        Doctor d = getDoctor();
        StatusAnswer actual = providerXML.insertDoctor(d);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertDataDoctor [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(2)
    void insertPatient() {
        log.debug("insertPatient [1]: - start working test ");
        Patient p = getPatient();
        StatusAnswer actual = providerXML.insertPatient(p);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertPatient [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(3)
    void insertPreparation() {
        log.debug("insertPreparation [1]: - start working test ");
        Preparation p = getPreparation();
        StatusAnswer actual = providerXML.insertPreparation(p);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertPreparation [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(4)
    void insertSchedule() {
        log.debug("insertSchedule [1]: - start working test ");
        Schedule s = getSchedule();
        StatusAnswer actual = providerXML.insertSchedule(s);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertSchedule [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(5)
    void insertCalcReport() {
        log.debug("insertCalcReport [1]: - start working test ");
        CalcReport s = getCalcReport();
        StatusAnswer actual = providerXML.insertCalcReport(s);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertCalcReport [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(6)
    void selectDoctorId() {
        log.debug("selectDoctorId [1]: - start working test");
        Optional<Doctor> actual =  providerXML.selectDoctorId(1);
        assertEquals(actual.get(),getDoctor());
        log.debug("selectDoctorId [2]: - end working test");
    }

    @Test
    @Order(7)
    void selectPatientId() {
        log.debug("selectPatientId [1]: - start working test");
        Optional<Patient> actual =  providerXML.selectPatientId(1);
        assertEquals(actual.get(),getPatient());
        log.debug("selectPatientId [2]: - end working test");
    }

    @Test
    @Order(8)
    void selectScheduleId() {
        log.debug("selectScheduleId [1]: - start working test");
        Optional<Schedule> actual =  providerXML.selectScheduleId(1);
        assertEquals(actual.get(),getSchedule());
        log.debug("selectScheduleId [2]: - end working test");
    }

    @Test
    @Order(9)
    void selectPreparationId() {
        log.debug("selectPreparationId [1]: - start working test");
        Optional<Preparation> actual =  providerXML.selectPreparationId(1);
        assertEquals(actual.get(),getPreparation());
        log.debug("selectPreparationId [2]: - end working test");
    }

    @Test
    @Order(10)
    void selectCalcReport() {
        log.debug("selectCalcReport [1]: - start working test");
        Optional<CalcReport> actual =  providerXML.selectCalcReport(1);
        assertEquals(actual.get(),getCalcReport());
        log.debug("selectCalcReport [2]: - end working test");

    }

    @Test
    @Order(11)
    void deleteDoctor() {
        log.debug("deleteDoctor [1]: - start working test");
        StatusAnswer actual =  providerXML.deleteDoctor(1);
        assertTrue(providerXML.selectDoctorId(1).isEmpty());
        log.debug("deleteDoctor [2]: - end working test");

    }

    @Test
    @Order(12)
    void selectDoctorIdNegativity() {
        log.debug("selectDoctorIdNegativity [1]: - start working test");
        Optional<Doctor> actual =  providerXML.selectDoctorId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectDoctorIdNegativity [2]: - end working test");
    }

    @Test
    @Order(13)
    void selectPatientIdNegativity() {
        log.debug("selectPatientIdNegativity [1]: - start working test");
        Optional<Patient> actual =  providerXML.selectPatientId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectPatientIdNegativity [2]: - end working test");
    }

    @Test
    @Order(14)
    void selectScheduleIdNegativity() {
        log.debug("selectScheduleIdNegativity [1]: - start working test");
        Optional<Schedule> actual =  providerXML.selectScheduleId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectScheduleIdNegativity [2]: - end working test");
    }

    @Test
    @Order(15)
    void selectPreparationIdNegativity() {
        log.debug("selectPreparationIdNegativity [1]: - start working test");
        Optional<Preparation> actual =  providerXML.selectPreparationId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectPreparationIdNegativity [2]: - end working test");
    }

    @Test
    @Order(16)
    void selectCalcReportNegativity() {
        log.debug("selectCalcReportNegativity [1]: - start working test");
        Optional<CalcReport> actual =  providerXML.selectCalcReport(0);
        assertTrue(actual.isEmpty());
        log.debug("selectCalcReportNegativity [2]: - end working test");

    }
}