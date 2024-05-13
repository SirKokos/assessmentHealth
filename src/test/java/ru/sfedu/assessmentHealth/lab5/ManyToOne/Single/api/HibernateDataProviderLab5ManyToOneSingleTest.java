package ru.sfedu.assessmentHealth.lab5.ManyToOne.Single.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import ru.sfedu.assessmentHealth.lab5.ManyToOne.Single.model.*;


import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab5ManyToOneSingleTest extends BaseTestLab5ManyToOneSingle{


    /**
     *  save obj
     *  type : Positive
     */
    @Order(1)
    @Test
    void saveRecordTest(){

        log.debug("saveRecordTest [1]: process load obj BD");
        hibernateDataProviderLab5ManyToOneSingle.saveRecord(getPatient());
        HistoryMedical historyMedical = getHistoryMedical();
        historyMedical.setPatient(getPatient());
        StatusResponse expectedHisMed = hibernateDataProviderLab5ManyToOneSingle.saveRecord(historyMedical);
        assertEquals(expectedHisMed, StatusResponse.OK);
        log.debug("saveRecordTest [2]: end working");
    }
    /**
     *  save obj
     *  type : Negative
     */
    @Order(2)
    @Test
    void saveRecordTest_negative() {
        log.debug("saveRecordTest_negative [1]: process save obj BD");
        HistoryMedical historyMedical = getHistoryMedical();
        historyMedical.setPatient(null);
        StatusResponse expectedHisMed = hibernateDataProviderLab5ManyToOneSingle.saveRecord(historyMedical);
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
        HistoryMedical expectedMedHis = (HistoryMedical) hibernateDataProviderLab5ManyToOneSingle.getRecord(HistoryMedical.class,1);
        assertEquals(getHistoryMedical(), expectedMedHis);
        log.debug("getRecordTest [2]: result {},",expectedMedHis);
    }
    /**
     *  get obj
     *  type : Negative
     */
    @Order(4)
    @Test
    void getRecordTest_negative() {
        log.debug("getRecordTest_negative [1]: process select obj ");
        HistoryMedical expectedMedHis = (HistoryMedical) hibernateDataProviderLab5ManyToOneSingle.getRecord(HistoryMedical.class,5);
        assertNull(expectedMedHis);
        log.debug("getRecordTest_negative [2]: result  = {}", expectedMedHis);
    }

    /**
     *  del obj
     *  type : Positive
     */
    @Order(5)
    @Test
    void deleteRecordTest() {
        log.debug("deleteRecordTest [1]: process select obj ");
        HistoryMedical historyMedical = getHistoryMedical();
        StatusResponse expected = hibernateDataProviderLab5ManyToOneSingle.deleteRecord(historyMedical);
        assertEquals(expected,StatusResponse.OK);
        log.debug("deleteRecordTest [2]: end working");

    }
    /**
     *  del obj
     *  type : Negative
     */
    @Order(6)
    @Test
    void deleteRecordTest_negative() {
        log.debug("deleteRecordTest_negative [1]: process delete obj ");
        StatusResponse expected = hibernateDataProviderLab5ManyToOneSingle.deleteRecord(null);
        assertEquals( expected,StatusResponse.ERROR);
        log.debug("deleteRecordTest_negative [2]: end working");
    }


    /**
     *  update obj
     *  type : Positive
     */
    @Order(7)
    @Test
    void updateRecordTest() {
        log.debug("updateTestEntity [1]: process update TestEntity ");
        hibernateDataProviderLab5ManyToOneSingle.saveRecord(getHistoryMedical());
        HistoryMedical obj = getHistoryMedical();
        obj.setFIODoctor("Update name ");
        StatusResponse expected = hibernateDataProviderLab5ManyToOneSingle.updateRecord(obj);
        log.debug("updateTestEntity [2]: end working {}",expected);
        assertEquals(expected,StatusResponse.OK);

    }

    /**
     *  update obj
     *  type : Negative
     */
    @Order(8)
    @Test
    void updateRecordTest_negative() {
        log.debug("updateTestEntity_negative [1]: process update TestEntity ");
        HistoryMedical obj = getHistoryMedical();
        obj.setId(2);
        obj.setFIODoctor("Update name ");
        StatusResponse expected = hibernateDataProviderLab5ManyToOneSingle.updateRecord(obj);
        assertEquals(expected,StatusResponse.ERROR);
        log.debug("updateTestEntity_negative [2]: end working");
    }
}