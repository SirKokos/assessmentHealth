package ru.sfedu.assessmentHealth.lab2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.sfedu.assessmentHealth.lab1.api.HibernateDataProviderPostgres;


import ru.sfedu.assessmentHealth.lab2.model.StatusResponse;
import ru.sfedu.assessmentHealth.lab2.model.TestEntity;



public class HibernateDataProviderLab2 extends HibernateDataProviderPostgres{
    private static final Logger log = LogManager.getLogger(HibernateDataProviderLab2.class.getName());

    /**
     * Method for test save obj
     * @param testEntity - my test obj
     */
    public StatusResponse saveTestEntity(TestEntity testEntity){
        log.debug("saveTestEntity [1]: start method save");
        StatusResponse statusResponse = StatusResponse.ERROR;
        if(session == null ){
            session = getSession();
        }
        try {
            session.beginTransaction();
            session.save(testEntity);
            session.getTransaction().commit();
            session.close();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("saveTestEntity [2]: ERROR {}",e.getMessage());
        }
        log.debug("saveTestEntity [3]: end working");
        return statusResponse;
    }

    /**
     * Select testEntity
     * @param id - obj ib bd
     * @return TestEntity
     */
    public TestEntity selectTestEntity(Long id){
        log.debug("selectTestEntity [1]: select TestEntity");
        TestEntity testEntity = new TestEntity();
        if(session == null ){
            session = getSession();
        }
        try {
            session.beginTransaction();
            testEntity =  session.get(TestEntity.class,id);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            log.error("selectTestEntity [2]: ERROR {}",e.getMessage());
        }

        log.debug("selectTestEntity [3]: end working");

        return testEntity;
    }


    /**
     * Delete testEntity
     * @param id - obj ib bd
     * @return StatusResponse enum (ok/Error)
     */
    public StatusResponse deleteTestEntity(Long id){
        log.debug("deleteTestEntity [1]: delete TestEntity");
        StatusResponse statusResponse = StatusResponse.ERROR;
        if(session == null ){
            session = getSession();
        }
        try {
            session.beginTransaction();
            TestEntity testEntity = session.get(TestEntity.class, id);
            session.delete(testEntity);
            session.getTransaction().commit();
            session.close();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("deleteTestEntity [2]: ERROR {}",e.getMessage());
        }
        log.debug("deleteTestEntity [3]: End working");
        return statusResponse;
    }

    /**
     * Update testEntity
     * @param testEntity - obj for update with id
     * @return TestEntity - obj after update
     */
    public TestEntity updateTestEntity(TestEntity testEntity){
        log.debug("updateTestEntity [1]: update obj");
        if(session == null ){
            session = getSession();
        }
        try {
            session.beginTransaction();
            session.update(testEntity);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            log.error("updateTestEntity [2]: ERROR {}" ,e.getMessage());
        }
        log.debug("updateTestEntity [3]: end working");
        return testEntity;
    }




}
