package ru.sfedu.assessmentHealth.lab5.ManyToMany.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import ru.sfedu.assessmentHealth.lab5.ManyToMany.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab5ManyToManyTest extends BaseTestLab5ManyToMany{


    /**
     *  save obj
     *  type : Positive
     */
    @Order(1)
    @Test
    void saveRecordTest(){
        log.debug("saveRecordTest [1]: process load obj BD");
        Doctor doctor = getDoctor();
        Patient patient = getPatient();
        doctor.getPatients().add(patient);
        patient.getDoctors().add(doctor);
        hibernateDataProviderLab5ManyToMany.saveRecord(patient);
        StatusResponse expectedDoc = hibernateDataProviderLab5ManyToMany.saveRecord(doctor);

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
        doctor.setId(2);
        Patient patient = getPatient();
        patient.setId(2);
        doctor.getPatients().add(patient);
        patient.getDoctors().add(doctor);
        StatusResponse expectedDoc = hibernateDataProviderLab5ManyToMany.saveRecord(doctor);
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
        Doctor expectedDoc = (Doctor) hibernateDataProviderLab5ManyToMany.getRecord(Doctor.class,1);
        assertEquals(getDoctor(), expectedDoc);
        log.debug("getRecordTest [2]: result -----> {},",expectedDoc.getPatients().iterator().next().getFio());
    }
    /**
     *  get obj
     *  type : Negative
     */
    @Order(4)
    @Test
    void getRecordTest_negative() {
        log.debug("getRecordTest_negative [1]: process select obj ");
        Doctor expectedDoc = (Doctor) hibernateDataProviderLab5ManyToMany.getRecord(Doctor.class,5);
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
        doctor.setFio("Update");
        StatusResponse expected = hibernateDataProviderLab5ManyToMany.updateRecord(doctor);
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
        doctor.setId(10);
        doctor.setFio("Update name ");
        StatusResponse expected = hibernateDataProviderLab5ManyToMany.updateRecord(doctor);
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
        Person doctor = getDoctor();
        StatusResponse expected = hibernateDataProviderLab5ManyToMany.deleteRecord(doctor);
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

        StatusResponse expected = hibernateDataProviderLab5ManyToMany.deleteRecord(null);
        assertEquals( expected,StatusResponse.ERROR);
        log.debug("deleteRecordTest_negative [2]: end working");
    }


    @Test
    @Order(9)
    void selectAllNativeSql() {
        log.debug("selectAllNativeSql [1]: start working");
        List<Preparation> preparationList = generateEntity();
        preparationList.forEach(preparation -> hibernateDataProviderLab5ManyToMany.saveRecord(preparation));
        long start = System.currentTimeMillis();
        List expected = hibernateDataProviderLab5ManyToMany.selectAllNativeSql();
        log.debug("selectAllNativeSql [2]: time ============> {}",System.currentTimeMillis()-start);
    }

    @Test
    @Order(10)
    void selectAllCriteria() {
        log.debug("selectAllCriteria [1]: start working");
        List<Preparation> preparationList = generateEntity();
        preparationList.forEach(preparation -> hibernateDataProviderLab5ManyToMany.saveRecord(preparation));
        long start = System.currentTimeMillis();
        List expected = hibernateDataProviderLab5ManyToMany.selectAllCriteria();
        log.debug("selectAllCriteria [2]: time ============> {}",System.currentTimeMillis()-start);
    }

    @Test
    @Order(11)
    void selectAllHQL() {
        log.debug("selectAllCriteria [1]: start working");
        List<Preparation> preparationList = generateEntity();
        preparationList.forEach(preparation -> hibernateDataProviderLab5ManyToMany.saveRecord(preparation));
        long start = System.currentTimeMillis();
        List expected = hibernateDataProviderLab5ManyToMany.selectAllHQL();
        log.debug("selectAllCriteria [2]: time ============> {}",System.currentTimeMillis()-start);
    }
}