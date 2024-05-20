package ru.sfedu.assessmentHealth.lab5.OneToOne.SplitKey.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.sfedu.assessmentHealth.lab5.OneToOne.SplitKey.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab5OneToOneSplitKeyTest extends BaseTestLab5OneToOneSingleKey{


    /**
     *  save obj
     *  type : Positive
     */
    @Order(1)
    @Test
    void saveRecordTest(){
        log.debug("saveRecordTest [1]: process load obj BD");
        Doctor doctor = getDoctor();
        Address address = getAddress();
        doctor.setShippingAddress(address);

        hibernateDataProviderLab5OneToOneSplitKey.saveRecord(address);
        StatusResponse expectedDoc = hibernateDataProviderLab5OneToOneSplitKey.saveRecord(doctor);

        assertEquals(expectedDoc, StatusResponse.OK);
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
        Doctor doctor = getDoctor();
        StatusResponse expectedDoc = hibernateDataProviderLab5OneToOneSplitKey.saveRecord(doctor);
        assertEquals(expectedDoc, StatusResponse.ERROR);
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
        Doctor expectedDoc = (Doctor) hibernateDataProviderLab5OneToOneSplitKey.getRecord(Doctor.class,1);
        assertEquals(getDoctor(), expectedDoc);
        log.debug("getRecordTest [2]: result -----> {},",expectedDoc.getFio());
    }
    /**
     *  get obj
     *  type : Negative
     */
    @Order(4)
    @Test
    void getRecordTest_negative() {
        log.debug("getRecordTest_negative [1]: process select obj ");
        Doctor expectedDoc = (Doctor) hibernateDataProviderLab5OneToOneSplitKey.getRecord(Doctor.class,5);
        assertNull(expectedDoc);
        log.debug("getRecordTest_negative [2]: result  = {}", expectedDoc);
    }


    /**
     *  update obj
     *  type : Positive
     */
    @Order(5)
    @Test
    void updateRecordTest() {
        log.debug("updateTestEntity [1]: process update TestEntity ");
//        hibernateDataProviderLab5ManyToOneTwo.saveRecord(getPatient());
        Address address = getAddress();
        Doctor doctor = getDoctor();

        address.setZipCode("Update name ");
        doctor.setId(1);
        address.setId(1);
        doctor.setShippingAddress(address);

        StatusResponse expected = hibernateDataProviderLab5OneToOneSplitKey.updateRecord(address);
        hibernateDataProviderLab5OneToOneSplitKey.updateRecord(doctor);
        log.debug("updateTestEntity [2]: end working {}",expected);
        assertEquals(expected,StatusResponse.OK);

    }

    /**
     *  update obj
     *  type : Negative
     */
    @Order(6)
    @Test
    void updateRecordTest_negative() {
        log.debug("updateTestEntity_negative [1]: process update TestEntity ");
        HistoryMedical obj = getHistoryMedical();
        obj.setId(2);
        obj.setFIODoctor("Update name ");
        StatusResponse expected = hibernateDataProviderLab5OneToOneSplitKey.updateRecord(obj);
        assertEquals(expected,StatusResponse.ERROR);
        log.debug("updateTestEntity_negative [2]: end working");
    }
    /**
     *  del obj
     *  type : Positive
     */
    @Order(7)
    @Test
    void deleteRecordTest() {
        log.debug("deleteRecordTest [1]: process select obj ");
        Doctor doctor = getDoctor();
        doctor.setId(1);
        Address address = getAddress();
        address.setId(1);
        hibernateDataProviderLab5OneToOneSplitKey.deleteRecord(address);
        StatusResponse expected = hibernateDataProviderLab5OneToOneSplitKey.deleteRecord(doctor);
        assertEquals(expected,StatusResponse.OK);
        log.debug("deleteRecordTest [2]: end working");

    }
    /**
     *  del obj
     *  type : Negative
     */
    @Order(8)
    @Test
    void deleteRecordTest_negative() {
        log.debug("deleteRecordTest_negative [1]: process delete obj ");
        Address address = getAddress();
        address.setId(10);
        StatusResponse expected = hibernateDataProviderLab5OneToOneSplitKey.deleteRecord(address);
        assertEquals( expected,StatusResponse.ERROR);
        log.debug("deleteRecordTest_negative [2]: end working");
    }

}