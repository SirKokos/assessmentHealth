package ru.sfedu.assessmentHealth.lab5.ManyToOne.Two.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import ru.sfedu.assessmentHealth.lab5.ManyToOne.Two.model.HistoryMedical;
import ru.sfedu.assessmentHealth.lab5.ManyToOne.Two.model.Patient;
import ru.sfedu.assessmentHealth.lab5.ManyToOne.Two.model.StatusResponse;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab5ManyToOneTwoTest extends BaseTestLab5ManyToOneTwo{


    /**
     *  save obj
     *  type : Positive
     */
    @Order(1)
    @Test
    void saveRecordTest(){

        log.debug("saveRecordTest [1]: process load obj BD");
        Patient patient = getPatient();
        HistoryMedical historyMedical = getHistoryMedical();

        patient.setId(1);
        historyMedical.setId(1);
        historyMedical.setPatient(patient);
        patient.getHistoryMedicals().add(historyMedical);
        hibernateDataProviderLab5ManyToOneTwo.saveRecord(historyMedical);

        StatusResponse expectedHisMed = hibernateDataProviderLab5ManyToOneTwo.saveRecord(patient);


        assertEquals(expectedHisMed, StatusResponse.OK);
        log.debug("saveRecordTest [2]: end working");
    }
    /**
     *  save obj
     *  type : Negative
     */
    @Order(2)
    @Disabled
    @Test
    void saveRecordTest_negative() {
        log.debug("saveRecordTest_negative [1]: process save obj BD");
        Patient patient = getPatient();
        patient.setHistoryMedicals(null);
        StatusResponse expectedHisMed = hibernateDataProviderLab5ManyToOneTwo.saveRecord(patient);
        assertEquals(expectedHisMed, StatusResponse.ERROR);
        log.debug("saveRecordTest_negative [2]: end working");
    }

    /**
     *  get obj
     *  type : Positive
     */
    @Order(3)
    @Test
    void getRecordTest() {
        log.debug("getRecordTest [1]: process select obj ");
        Patient expectedMedHis = (Patient) hibernateDataProviderLab5ManyToOneTwo.getRecord(Patient.class,1);
        assertEquals(getPatient(), expectedMedHis);
        log.debug("getRecordTest [2]: result -----> {},",expectedMedHis.getHistoryMedicals().size());
    }
    /**
     *  get obj
     *  type : Negative
     */
    @Order(4)
    @Disabled
    @Test
    void getRecordTest_negative() {
        log.debug("getRecordTest_negative [1]: process select obj ");
        Patient expectedPat = (Patient) hibernateDataProviderLab5ManyToOneTwo.getRecord(Patient.class,5);
        assertNull(expectedPat);
        log.debug("getRecordTest_negative [2]: result  = {}", expectedPat);
    }

    /**
     *  del obj
     *  type : Positive
     */
    @Order(5)
    @Disabled
    @Test
    void deleteRecordTest() {
        log.debug("deleteRecordTest [1]: process select obj ");
        Patient patient = getPatient();
        StatusResponse expected = hibernateDataProviderLab5ManyToOneTwo.deleteRecord(patient);
        assertEquals(expected,StatusResponse.OK);
        log.debug("deleteRecordTest [2]: end working");

    }
    /**
     *  del obj
     *  type : Negative
     */
    @Order(6)
    @Disabled
    @Test
    void deleteRecordTest_negative() {
        log.debug("deleteRecordTest_negative [1]: process delete obj ");
        Patient patient = getPatient();
        patient.setId(10);
        StatusResponse expected = hibernateDataProviderLab5ManyToOneTwo.deleteRecord(patient);
        assertEquals( expected,StatusResponse.ERROR);
        log.debug("deleteRecordTest_negative [2]: end working");
    }


    /**
     *  update obj
     *  type : Positive
     */
    @Order(7)
    @Disabled
    @Test
    void updateRecordTest() {
        log.debug("updateTestEntity [1]: process update TestEntity ");
//        hibernateDataProviderLab5ManyToOneTwo.saveRecord(getPatient());
        HistoryMedical obj = getHistoryMedical();
        obj.setFIODoctor("Update name ");
        Patient patient = getPatient();
        patient.getHistoryMedicals().add(obj);
        StatusResponse expected = hibernateDataProviderLab5ManyToOneTwo.updateRecord(patient);
        log.debug("updateTestEntity [2]: end working {}",expected);
        assertEquals(expected,StatusResponse.OK);

    }

    /**
     *  update obj
     *  type : Negative
     */
    @Order(8)
    @Disabled
    @Test
    void updateRecordTest_negative() {
        log.debug("updateTestEntity_negative [1]: process update TestEntity ");
        HistoryMedical obj = getHistoryMedical();
        obj.setId(2);
        obj.setFIODoctor("Update name ");
        StatusResponse expected = hibernateDataProviderLab5ManyToOneTwo.updateRecord(obj);
        assertEquals(expected,StatusResponse.ERROR);
        log.debug("updateTestEntity_negative [2]: end working");
    }
}