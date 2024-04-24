package ru.sfedu.assessmentHealth.lab1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.lab1.utils.HibernateUtil;
import ru.sfedu.assessmentHealth.lab4.api.HibernateDataProviderLab4;
import ru.sfedu.assessmentHealth.model.StatusAnswer;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateDataProviderPostgresTest {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderPostgresTest.class.getName());

    HibernateDataProviderPostgres hibernateDataProviderPostgres = new HibernateDataProviderPostgres();
    static {
        PropertyConfig.setConfigPath(Const.NAME_PROPERTY_FILE);
        HibernateUtil.setPathConfig(PropertyConfig.getPropertyValue(Const.LAB1_HBN_CFG,Const.NAME_PROPERTY_FILE));
    }

    /**
     * Open session
     * type: Positive
     */
    @Test
    void getSession() {
        log.debug("getSession[1]: Test start working");
        HibernateDataProviderPostgres.getSession();
        log.debug("getSession[2]: Test end working");
    }
    /**
     * Open session
     * type: Negative
     */
    @Test
    void getSessionNegative() {
        log.debug("getSession[1]: Test start working");
        Session expected = HibernateDataProviderPostgres.getSession();
        assertNotNull(expected);
        log.debug("getSession[2]: Test end working");
    }

    /**
     * a method for making requests
     * type: Positive
     */
    @Test
    void executeQuery() {
        log.debug("executeQuery[1]: Test start working");
        NativeQuery executeQuery = HibernateDataProviderPostgres.executeQuery(Const.HIBERNATE_QUERY_LAB1_GET_LIST_TABLE);
        log.debug("executeQuery[2]: Test end working result = {}",executeQuery.list());
    }
    /**
     * a method for making requests
     * type: Negative
     */
    @Test
    void executeQueryNegative() {
        log.debug("executeQueryNegative[1]: Test start working");
        NativeQuery executeQuery = HibernateDataProviderPostgres.executeQuery(Const.HIBERNATE_QUERY_LAB1_GET_LIST_TABLE);
        assertFalse(executeQuery.list().isEmpty());
        log.debug("executeQueryNegative[2]: Test end working result = {}",executeQuery.list());
    }

    /**
     * get size table
     * type: Positive
     */
    @Test
    void getSizeTable() {
        log.debug("getSizeTable[1]: Test start working");
        NativeQuery size = hibernateDataProviderPostgres.getSizeTable("doctor");
        log.debug("getSizeTable[2]: Test result = {} \nSQL = {}",size.getSingleResult(),Const.HIBERNATE_QUERY_LAB1_GET_SIZE_TABLE);
    }
    /**
     * get size table
     * type: Negativity
     */
    @Test
    void getSizeTableNegativity() {
        log.debug("getSizeTableNegativity[1]: Test start working");
        NativeQuery size = hibernateDataProviderPostgres.getSizeTable("doctor");
        assertNotEquals(size,0);
        log.debug("getSizeTableNegativity[2]: Test result = {} \nSQL = {}",size.getSingleResult(),Const.HIBERNATE_QUERY_LAB1_GET_SIZE_TABLE);
    }


    /**
     *  get List tables
     *  type : Positive
     */
    @Test
    void getListTables() {
        log.debug("getListTables[1]: Test start working");
        List<String> list = hibernateDataProviderPostgres.getListTables();
        log.debug("getListTables[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_TABLE);
    }
    /**
     *  get List tables
     *  type : Negative
     */
    @Test
    void getListTablesNegative() {
        log.debug("getListTablesNegative[1]: Test start working");
        List<String> list = hibernateDataProviderPostgres.getListTables();
        assertFalse(list.isEmpty());
        log.debug("getListTablesNegative[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_TABLE);
    }


    /**
     * method get User
     * type : Positive
     */
    @Test
    void getUsers() {
        log.debug("getUsers[1]: Test start working");
        List<String> list = hibernateDataProviderPostgres.getUsers();
        log.debug("getUsers[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_USERS);
    }

    /**
     * method get User
     * type : Negative
     */
    @Test
    void getUsersNegative() {
        log.debug("getUsersNegative[1]: Test start working");
        List list = hibernateDataProviderPostgres.getUsers();
        assertFalse(list.isEmpty());
        log.debug("getUsersNegative[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_USERS);
    }
    /**
     * get meta data
     * type : Positive
     */
    @Test
    void getInfoValues() {
        log.debug("getInfoValues[1]: Test start working");
        List<String> list = hibernateDataProviderPostgres.getInfoValues("doctor");
        log.debug("getInfoValues[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_INFO);
    }

    /**
     * get meta data
     * type : Negative
     */
    @Test
    void getInfoValuesNegative() {
        log.debug("getInfoValues_InvalidEntityName[1]: Test start working");
        List list = hibernateDataProviderPostgres.getInfoValues("invalid_entity_name");
        log.debug("getInfoValues_InvalidEntityName[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_INFO);
        assertTrue(list.isEmpty());
    }
}