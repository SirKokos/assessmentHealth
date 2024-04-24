package ru.sfedu.assessmentHealth.lab3.Joined.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import ru.sfedu.assessmentHealth.lab3.Joined.model.Doctor;
import ru.sfedu.assessmentHealth.lab3.Joined.model.Patient;
import ru.sfedu.assessmentHealth.lab3.Joined.model.Person;
import ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model.StatusResponse;

import javax.print.Doc;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab3Test extends BaseTestLab3{



    /**
     *  save obj
     *  type : Positive
     */
    @Order(1)
    @Test
    void saveRecordTest(){
        log.debug("saveRecordTest [1]: process load obj BD");
        StatusResponse expected =hibernateDataProviderLab3.saveRecord(getDoctor());
        assertEquals(expected,StatusResponse.OK);
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
        StatusResponse expected = hibernateDataProviderLab3.saveRecord(null);
        assertEquals(StatusResponse.ERROR, expected);
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
        Doctor expected = (Doctor) hibernateDataProviderLab3.getRecord(Doctor.class,1);
        assertEquals(getDoctor(), expected);
        log.debug("getRecordTest [2]: result  = {}",expected);
    }
    /**
     *  get obj
     *  type : Negative
     */
    @Order(4)
    @Test
    void getRecordTest_negative() {
        log.debug("getRecordTest_negative [1]: process select obj ");
        Doctor expected = (Doctor) hibernateDataProviderLab3.getRecord(Doctor.class,5);
        assertNull(expected);
        log.debug("getRecordTest_negative [2]: result  = {}", expected);
    }

    /**
     *  del obj
     *  type : Positive
     */
    @Order(5)
    @Test
    void deleteRecordTest() {
        log.debug("deleteRecordTest [1]: process select obj ");
        StatusResponse expected = hibernateDataProviderLab3.deleteRecord(getDoctor());
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
        Doctor obj = getDoctor();
        obj.setId(10);
        StatusResponse expected = hibernateDataProviderLab3.deleteRecord(obj);
        assertEquals(StatusResponse.ERROR, expected);
        log.debug("deleteRecordTest_negative [2]: end working");
    }


    /**
     *  update obj
     *  type : Positive
     */
    @Order(7)
//    @Disabled
    @Test
    void updateRecordTest() {
        log.debug("updateTestEntity [1]: process update TestEntity ");
        hibernateDataProviderLab3.saveRecord(getDoctor());
        Doctor obj = getDoctor();
        obj.setId(2);
        obj.setAge(60);
        obj.setFio("Arte");
        StatusResponse expected = hibernateDataProviderLab3.updateRecord(obj);
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
        Doctor obj = getDoctor();
        obj.setId(10);
        obj.setAge(60);
        obj.setFio("BobBOOO");
        StatusResponse expected = hibernateDataProviderLab3.updateRecord(obj);
        assertEquals(expected,StatusResponse.ERROR);
        log.debug("updateTestEntity_negative [2]: end working");
    }

}