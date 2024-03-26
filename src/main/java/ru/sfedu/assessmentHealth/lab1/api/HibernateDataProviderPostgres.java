package ru.sfedu.assessmentHealth.lab1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.lab1.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class HibernateDataProviderPostgres {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderPostgres.class.getName());
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
     * Method generate sql query
     * @param query String sql query
     * @return nativeQuery result query
     */
    public static NativeQuery executeQuery(String query){
        log.debug("executeQuery [1]: start execute");
        NativeQuery nativeQuery = null;
        if(session == null ){
            session = getSession();
        }
        try {
            nativeQuery = session.createNativeQuery(query);
        }catch (Exception e){
            log.error("executeQuery [2]: Error {}",e.getMessage());
        }
        return nativeQuery;
    }

    /**
     * Method for get Size table
     * @param nameTable name table in BD
     * @return NativeQuery
     */
    public NativeQuery getSizeTable(String nameTable){
        NativeQuery sizeTable = null;
        log.debug("getSizeTable [1]: start get size table");
        try {
            sizeTable = executeQuery(String.format(Const.HIBERNATE_QUERY_LAB1_GET_SIZE_TABLE, nameTable));
        }catch (Exception e){
            log.error("getSizeTable [2]: ERROR {}",e.getMessage());
        }
        log.debug("getSizeTable [3]: end working");
        return sizeTable;
    }

    /**
     * Method for get all list table in BD
     * @return List<String> list name table
     */
    public List<String> getListTables(){
        log.debug("getListTables [1]: start get list table");
        NativeQuery listTables = null;
        try {
            listTables = executeQuery(Const.HIBERNATE_QUERY_LAB1_GET_LIST_TABLE);
        }catch (Exception e){
            log.error("getListTables [2]: ERROR {}",e.getMessage());
        }
        log.debug("getListTables [3]: end working");
        return listTables.list();
    }


    /**
     * Method for get all list user in BD
     * @return List<String> list users in BD
     */
    public List<String> getUsers(){
        log.debug("getUsers [1]: start get list user");
        NativeQuery listUsers = null;
        try {
            listUsers = executeQuery(Const.HIBERNATE_QUERY_LAB1_GET_LIST_USERS);
        }catch (Exception e){
            log.error("getUsers [2]: ERROR {}",e.getMessage());
        }
        log.debug("getUsers [3]: end working");
        return listUsers.list();
    }

    /**
     * Method get info about table type
     * @param tableName name table in BD
     * @return List<String> name table with type
     */
    public List<String> getInfoValues(String tableName){
        log.debug("getInfoValues [1]: start get list user");
        NativeQuery listUsers = null;
        try {
            listUsers = executeQuery(String.format(Const.HIBERNATE_QUERY_LAB1_GET_LIST_INFO,tableName));
        }catch (Exception e){
            log.error("getInfoValues [3]: ERROR {}",e.getMessage());
        }
        log.debug("getInfoValues [2]: end working");
        return listUsers.list();
    }

}
