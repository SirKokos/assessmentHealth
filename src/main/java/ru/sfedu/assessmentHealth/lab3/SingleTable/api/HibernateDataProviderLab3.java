package ru.sfedu.assessmentHealth.lab3.SingleTable.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.assessmentHealth.lab1.api.HibernateDataProviderPostgres;
import ru.sfedu.assessmentHealth.lab3.SingleTable.model.StatusResponse;
import ru.sfedu.assessmentHealth.lab3.SingleTable.utils.HibernateUtil;


public class HibernateDataProviderLab3 extends HibernateDataProviderPostgres {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderLab3.class.getName());


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

    public void closeSession(){
        log.debug("closeSession [1]: close session");
        if(session != null && session.isOpen()){
            session.close();
        }
        log.debug("closeSession [2]: close session");
    }

    /**
     * insert
     * @param object - obj which want save
     * @return StatusResponse (ok/error)
     */
    public <T> StatusResponse saveRecord( T object){
        log.debug("saveRecord [1]: start method save");
        StatusResponse statusResponse = StatusResponse.ERROR;
        session = getSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(object);
            t.commit();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("saveRecord [2]: ERROR {}",e.getMessage());
        }finally {
            closeSession();
        }
        log.debug("saveRecord [3]: end working");
        return statusResponse;
    };

    /**
     * delete
     * @param object - obj which want del
     * @return StatusResponse (ok/error)
     */
    public <T> StatusResponse deleteRecord(T object) {
        log.debug("deleteRecord [1]: delete TestEntity");
        StatusResponse statusResponse = StatusResponse.ERROR;
        session = getSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(object);
            t.commit();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("deleteRecord [2]: ERROR {}",e.getMessage());
        }finally {
            closeSession();
        }
        log.debug("deleteRecord [3]: End working");
        return statusResponse;
    }

    /**
     * Update
     * @param object - obj which want update
     * @return StatusResponse (ok/error)
     */
    public <T> StatusResponse updateRecord(T object){
        log.debug("updateRecord [1]: update obj");
        StatusResponse statusResponse = StatusResponse.ERROR;
        session = getSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(object);
            t.commit();
            statusResponse = StatusResponse.OK;
        }catch (Exception e){
            log.error("updateRecord [2]: ERROR {}" ,e.getMessage());
        }finally {
            closeSession();
        }
        log.debug("updateRecord [3]: end working");
        return statusResponse;
    }

    /**
     * select
     * @param cl - type class which get
     * @param id - Id obj
     * @return Object
     */
    public Object getRecord(Class cl, Integer id){
        log.debug("getRecord [1]: select TestEntity");

        session = getSession();
        Transaction t = session.beginTransaction();
        Object object = new Object();
        try {
            object =  session.get(cl, id);
            t.commit();
        }catch (Exception e){
            log.error("getRecord [2]: ERROR {}",e.getMessage());
        }finally {
            closeSession();
        }

        log.debug("getRecord [3]: end working");

        return object;
    }

}
