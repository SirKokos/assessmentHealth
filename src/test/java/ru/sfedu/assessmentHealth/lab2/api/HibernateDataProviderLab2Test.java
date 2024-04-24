package ru.sfedu.assessmentHealth.lab2.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import ru.sfedu.assessmentHealth.lab2.model.StatusResponse;
import ru.sfedu.assessmentHealth.lab2.model.TestEntity;


import javax.persistence.Embeddable;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HibernateDataProviderLab2Test extends BaseTestLab2 {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderLab2Test.class.getName());


    /**
     *  save testEntity
     *  type : Positive
     */
    @Order(1)
    @Test
    void saveTestEntity(){
        log.debug("saveTestEntity [1]: process load obj BD");
        StatusResponse expected = hibernateDataProviderLab2.saveTestEntity(getTestEntity());
        assertEquals(expected,StatusResponse.OK);
        log.debug("saveTestEntity [2]: end working");
    }
    /**
     *  save testEntity
     *  type : Negative
     */
    @Order(2)
    @Test
    void saveTestEntity_negative() {
        log.debug("saveTestEntity_negative [1]: process save obj BD");
        StatusResponse expected = hibernateDataProviderLab2.saveTestEntity(null);
        assertEquals(StatusResponse.ERROR, expected);
        log.debug("saveTestEntity_negative [2]: end working");
    }

    /**
     *  get TestEntity
     *  type : Positive
     */
    @Order(3)
    @Test
    void selectTestEntity() {
        log.debug("selectTestEntity [1]: process select TestEntity ");
        TestEntity expected = hibernateDataProviderLab2.selectTestEntity(1L);
        assertEquals(getTestEntity(),expected);
        log.debug("selectTestEntity [2]: result  = {}",expected);
    }
    /**
     *  get TestEntity
     *  type : Negative
     */
    @Order(4)
    @Test
    void selectTestEntity_negative() {
        log.debug("selectTestEntity_negative [1]: process select TestEntity ");
        TestEntity expected = hibernateDataProviderLab2.selectTestEntity(5L);
        assertNull(expected);
        log.debug("selectTestEntity_negative [2]: result  = {}", expected);
    }

    /**
     *  del TestEntity
     *  type : Positive
     */
    @Order(5)
    @Test
    void deleteTestEntity() {
        log.debug("deleteTestEntity [1]: process select TestEntity ");
        hibernateDataProviderLab2.saveTestEntity(getTestEntity());
        StatusResponse expected = hibernateDataProviderLab2.deleteTestEntity(2L);
        assertEquals(expected,StatusResponse.OK);
        log.debug("deleteTestEntity [2]: end working");

    }
    /**
     *  del TestEntity
     *  type : Negative
     */
    @Order(6)
    @Test
    void deleteTestEntity_negative() {
        log.debug("deleteTestEntity_negative [1]: process delete TestEntity ");
        StatusResponse expected = hibernateDataProviderLab2.deleteTestEntity(10L);
        assertEquals(StatusResponse.ERROR, expected);
        log.debug("deleteTestEntity_negative [2]: end working");
    }


    /**
     *  update TestEntity
     *  type : Positive
     */
    @Order(7)
    @Test
    void updateTestEntity() {
        log.debug("updateTestEntity [1]: process update TestEntity ");
        TestEntity obj = getTestEntity();
        obj.setName("Bob");
        obj.setDescription("update");
        obj.setDateCreated(new Date());
        obj.setCheck(true);
        StatusResponse expected = hibernateDataProviderLab2.updateTestEntity(obj);
        log.debug("updateTestEntity [2]: end working {}",expected);
        assertEquals(expected,StatusResponse.OK);

    }

    /**
     *  update TestEntity
     *  type : Negative
     */
    @Order(8)
    @Test
    void updateTestEntity_negative() {
        log.debug("updateTestEntity_negative [1]: process update TestEntity ");
        TestEntity obj = getTestEntity();
        obj.setId(10L); //id does not exist
        obj.setName("Bob");
        obj.setDescription("update");
        obj.setDateCreated(new Date());
        obj.setCheck(true);
        obj.setPerson(getPerson());
        StatusResponse expected = hibernateDataProviderLab2.updateTestEntity(obj);
        assertEquals(expected,StatusResponse.ERROR);
        log.debug("updateTestEntity_negative [2]: end working");
    }


}