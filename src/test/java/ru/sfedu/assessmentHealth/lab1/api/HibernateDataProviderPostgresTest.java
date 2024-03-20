package ru.sfedu.assessmentHealth.lab1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.lab1.utils.HibernateUtil;

import java.util.List;

class HibernateDataProviderPostgresTest {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderPostgresTest.class.getName());

    HibernateDataProviderPostgres hibernateDataProviderPostgres = new HibernateDataProviderPostgres();

    @Test
    void getSession() {
        log.debug("getSession[1]: Test start working");
        HibernateDataProviderPostgres.getSession();
        log.debug("getSession[2]: Test end working");
    }

    @Test
    void executeQuery() {
        log.debug("executeQuery[1]: Test start working");
        NativeQuery executeQuery = HibernateDataProviderPostgres.executeQuery(Const.HIBERNATE_QUERY_LAB1_GET_LIST_TABLE);
        log.debug("executeQuery[2]: Test end working result = {}",executeQuery.list());
    }

    @Test
    void getSizeTable() {
        log.debug("getSizeTable[1]: Test start working");
        NativeQuery size = hibernateDataProviderPostgres.getSizeTable("doctor");
        log.debug("getSizeTable[2]: Test result = {} \nSQL = {}",size.getSingleResult(),Const.HIBERNATE_QUERY_LAB1_GET_SIZE_TABLE);
    }

    @Test
    void getListTables() {
        log.debug("getListTables[1]: Test start working");
        List<String> list = hibernateDataProviderPostgres.getListTables();
        log.debug("getListTables[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_TABLE);
    }

    @Test
    void getUsers() {
        log.debug("getUsers[1]: Test start working");
        List<String> list = hibernateDataProviderPostgres.getUsers();
        log.debug("getUsers[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_USERS);
    }

    @Test
    void getInfoValues() {
        log.debug("getInfoValues[1]: Test start working");
        List<String> list = hibernateDataProviderPostgres.getInfoValues("doctor");
        log.debug("getInfoValues[2]: Test result {} \nSQL = {}",list,Const.HIBERNATE_QUERY_LAB1_GET_LIST_INFO);
    }
}