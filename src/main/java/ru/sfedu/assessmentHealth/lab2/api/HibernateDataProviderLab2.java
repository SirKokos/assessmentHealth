package ru.sfedu.assessmentHealth.lab2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.Transaction;
import ru.sfedu.assessmentHealth.lab2.model.StatusResponse;
import ru.sfedu.assessmentHealth.lab2.model.TestEntity;
import ru.sfedu.assessmentHealth.lab2.utils.HibernateUtil;


public class HibernateDataProviderLab2 {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderLab2.class.getName());
    protected static Session session;



    /**
     * Open session.
     * @return session
     */
    public static Session getSession(){
        log.debug("getSession [1]: start get session");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        log.debug("getSession [2]: end work");
        return sessionFactory.openSession();
    }

    /**
     * Close session
     *
     */
    public void closeSession(){
        log.debug("closeSession [1]: close session");
        if(session != null && session.isOpen()){
            session.close();
        }
        log.debug("closeSession [2]: close session");
    }

    /**
     * Method for test save obj
     * @param testEntity - my test obj
     */
    public StatusResponse saveTestEntity(TestEntity testEntity){
        log.debug("saveTestEntity [1]: start method save");
        StatusResponse statusResponse = StatusResponse.ERROR;

        session = getSession();

        Transaction t = session.beginTransaction();
        try {
            session.save(testEntity);
            t.commit();

            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("saveTestEntity [2]: ERROR {}",e.getMessage());
        }finally {
            closeSession();
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
        session = getSession();
        Transaction t = session.beginTransaction();
        try {
            testEntity =  session.get(TestEntity.class,id);
            t.commit();
        }catch (Exception e){
            log.error("selectTestEntity [2]: ERROR {}",e.getMessage());
        }finally {
            closeSession();
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

        session = getSession();
        Transaction t = session.beginTransaction();
        try {
            TestEntity testEntity = session.get(TestEntity.class, id);

            session.delete(testEntity);
            t.commit();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("deleteTestEntity [2]: ERROR {}",e.getMessage());
        }finally {
            closeSession();
        }
        log.debug("deleteTestEntity [3]: End working");
        return statusResponse;
    }

    /**
     * Update testEntity
     * @param testEntity - obj for update with id
     * @return TestEntity - obj after update
     */
    public StatusResponse updateTestEntity(TestEntity testEntity){
        log.debug("updateTestEntity [1]: update obj");
        StatusResponse statusResponse = StatusResponse.ERROR;
        session = getSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(testEntity);
            t.commit();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("updateTestEntity [2]: ERROR {}" ,e.getMessage());
        }finally {
            closeSession();
        }
        log.debug("updateTestEntity [3]: end working");
        return statusResponse;
    }




}
