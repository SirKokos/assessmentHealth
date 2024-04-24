package ru.sfedu.assessmentHealth.lab3.TablePerClass.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.lab1.api.HibernateDataProviderPostgres;
import ru.sfedu.assessmentHealth.lab3.MappedSuperclass.model.StatusResponse;


public class HibernateDataProviderLab3 extends HibernateDataProviderPostgres {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderLab3.class.getName());


    /**
     * insert
     * @param object - obj which want save
     * @return StatusResponse (ok/error)
     */
    StatusResponse saveRecord(Object object){
        log.debug("saveRecord [1]: start method save");
        StatusResponse statusResponse = StatusResponse.ERROR;
        if(session == null ){
            session = getSession();
        }
        try {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            session.close();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("saveRecord [2]: ERROR {}",e.getMessage());
        }
        log.debug("saveRecord [3]: end working");
        return statusResponse;
    };

    /**
     * delete
     * @param object - obj which want del
     * @return StatusResponse (ok/error)
     */
    StatusResponse deleteRecord(Object object) {
        log.debug("deleteRecord [1]: delete TestEntity");
        StatusResponse statusResponse = StatusResponse.ERROR;
        if(session == null ){
            session = getSession();
        }
        try {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
            session.close();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("deleteRecord [2]: ERROR {}",e.getMessage());
        }
        log.debug("deleteRecord [3]: End working");
        return statusResponse;
    }

    /**
     * Update
     * @param object - obj which want update
     * @return StatusResponse (ok/error)
     */
    Object updateRecord(Object object){
        log.debug("updateRecord [1]: update obj");
        if(session == null ){
            session = getSession();
        }
        try {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            log.error("updateRecord [2]: ERROR {}" ,e.getMessage());
        }
        log.debug("updateRecord [3]: end working");
        return object;
    }

    /**
     * select
     * @param cl - type class which get
     * @param id - Id obj
     * @return Object
     */
    Object getRecord(Class cl, Integer id){
        log.debug("getRecord [1]: select TestEntity");
        if(session == null ){
            session = getSession();
        }
        Object object = new Object();
        try {
            session.beginTransaction();
            object =  session.get(cl, id);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            log.error("getRecord [2]: ERROR {}",e.getMessage());
        }

        log.debug("getRecord [3]: end working");

        return object;
    };





//    public StatusResponse saveB(B b){
//        log.debug("saveTestEntity [1]: start method save");
//        StatusResponse statusResponse = StatusResponse.ERROR;
//        if(session == null ){
//            session = getSession();
//        }
//        try {
//            session.beginTransaction();
//            session.save(b);
//            session.getTransaction().commit();
//            session.close();
//            statusResponse = StatusResponse.OK;
//        }catch (Exception e){
//            log.error("saveTestEntity [2]: ERROR {}",e.getMessage());
//        }
//        log.debug("saveTestEntity [3]: end working");
//        return statusResponse;
//    }

}
