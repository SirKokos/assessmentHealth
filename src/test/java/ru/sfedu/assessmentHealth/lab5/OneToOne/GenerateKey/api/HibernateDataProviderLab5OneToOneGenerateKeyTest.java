package ru.sfedu.assessmentHealth.lab5.OneToOne.GenerateKey.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import ru.sfedu.assessmentHealth.lab5.OneToOne.GenerateKey.model.*;


import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab5OneToOneGenerateKeyTest extends BaseTestLab5OneToOneGenerateKey{

    /**
     *  save obj
     *  type : Positive
     */
    @Order(1)
    @Test
    void saveRecordTest(){
        log.debug("saveRecordTest [1]: process load obj BD");
        Person doctor = getDoctor();
        Address address = getAddress();
        address.setPerson(doctor);
        doctor.setShippingAddress(address);



        StatusResponse expectedDoc = hibernateDataProviderLab5OneToOneGenerateKey.saveRecord(doctor);

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
        Doctor doctor = null;
        StatusResponse expectedDoc = hibernateDataProviderLab5OneToOneGenerateKey.saveRecord(doctor);
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
        Doctor expectedDoc = (Doctor) hibernateDataProviderLab5OneToOneGenerateKey.getRecord(Doctor.class,1);
        assertEquals(getDoctor(), expectedDoc);
        log.debug("getRecordTest [2]: result -----> {},",expectedDoc);
    }
    /**
     *  get obj
     *  type : Negative
     */
    @Order(4)
    @Test
    void getRecordTest_negative() {
        log.debug("getRecordTest_negative [1]: process select obj ");
        Doctor expectedDoc = (Doctor) hibernateDataProviderLab5OneToOneGenerateKey.getRecord(Doctor.class,5);
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
        Person doctor = getDoctor();
        doctor.setId(1);
        Address address = getAddress();
        address.setId(1);
        address.setPerson(doctor);
        doctor.setShippingAddress(address);
        address.setZipCode("Update name ");

        StatusResponse expected = hibernateDataProviderLab5OneToOneGenerateKey.updateRecord(address);
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
        Person doctor = getDoctor();
        Address address = getAddress();
        address.setPerson(doctor);
        doctor.setShippingAddress(address);
        doctor.setFio("Update name ");
        StatusResponse expected = hibernateDataProviderLab5OneToOneGenerateKey.updateRecord(doctor);
        assertEquals(expected,StatusResponse.ERROR);
        log.debug("updateTestEntity_negative [2]: end working");
    }
    /**
     *  del obj
     *  type : Positive
     */
    @Order(7)
//    @Disabled
    @Test
    void deleteRecordTest() {
        log.debug("deleteRecordTest [1]: process select obj ");
        Person doctor = getDoctor();
        Address address = getAddress();
        doctor.setId(1);
        address.setId(1);

        address.setPerson(doctor);
        doctor.setShippingAddress(address);
        StatusResponse expected = hibernateDataProviderLab5OneToOneGenerateKey.deleteRecord(doctor);
        assertEquals(expected,StatusResponse.OK);
        log.debug("deleteRecordTest [2]: end working");

    }
    /**
     *  del obj
     *  type : Negative
     */
    @Order(8)
//    @Disabled
    @Test
    void deleteRecordTest_negative() {
        log.debug("deleteRecordTest_negative [1]: process delete obj ");
        Address address = getAddress();
        address.setId(10);
        StatusResponse expected = hibernateDataProviderLab5OneToOneGenerateKey.deleteRecord(address);
        assertEquals( expected,StatusResponse.ERROR);
        log.debug("deleteRecordTest_negative [2]: end working");
    }
}