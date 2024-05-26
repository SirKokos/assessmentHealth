package ru.sfedu.assessmentHealth.lab5.ManyToMany.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.lab5.ManyToMany.model.Preparation;
import ru.sfedu.assessmentHealth.lab5.ManyToMany.model.StatusResponse;
import ru.sfedu.assessmentHealth.lab5.ManyToMany.utils.HibernateUtil;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class HibernateDataProviderLab5ManyToMany implements ISQL{
    private static final Logger log = LogManager.getLogger(HibernateDataProviderLab5ManyToMany.class.getName());


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
    public <T> StatusResponse saveRecord(T object){
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
    public <T>StatusResponse deleteRecord(T object) {
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
        log.debug("getRecord [1]: select Entity");

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

    @Override
    public List<Preparation> selectAllNativeSql() {
        log.debug("selectAllNativeSql [1]: select all Entity");
        String NativeSql = PropertyConfig.getPropertyValue(Const.HIBERNATE_NATIVE_SQL, Const.HIBERNATE_NATIVE_SQL_PATH);
        session = getSession();
        List<Preparation> results = null;
        try {
            NativeQuery query = session.createNativeQuery(NativeSql);
            results = query.getResultList();
        } catch (Exception e) {
            log.error("selectAllNativeSql [2]: ERROR {}", e.getMessage());
        } finally {
            closeSession();
        }
        log.debug("selectAllNativeSql [3]: end work");
        return results;
    }

    @Override
    public  List<Preparation> selectAllCriteria() {
        log.debug("selectAllCriteria [1]: select all Entity");
        session = getSession();
        Query query = null;
        List<Preparation> results = new ArrayList<>();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Preparation> critQuery = builder.createQuery(Preparation.class);

            Root<Preparation> root = critQuery.from(Preparation.class);
            critQuery.select(root);
            query = session.createQuery(critQuery);
            results = query.getResultList();
        }catch (Exception e){
            log.error("selectAllCriteria [2]: ERROR {}", e.getMessage());
        }finally {
            closeSession();
        }

        log.debug("selectAllCriteria [3]: end work");
        return results;
    }

    @Override
    public List<Preparation> selectAllHQL() {
        log.debug("selectAllHQL [1]: select all Entity");
        String hqlQuery = PropertyConfig.getPropertyValue(Const.HIBERNATE_HQL_SQL, Const.HIBERNATE_NATIVE_SQL_PATH);
        Query query = null;
        List<Preparation> results = new ArrayList<>();
        session = getSession();
        try {
            query = session.createQuery(hqlQuery);
            results = query.getResultList();
        }catch (Exception e){
            log.error("selectAllHQL [2]: ERROR {}", e.getMessage());
        }finally {
            closeSession();
        }

        log.debug("selectAllHQL [3]: end work");
        return results;
    }
}
