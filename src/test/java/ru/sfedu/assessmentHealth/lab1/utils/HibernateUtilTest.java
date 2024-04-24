package ru.sfedu.assessmentHealth.lab1.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.lab1.api.HibernateDataProviderPostgres;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilTest {
    private static final Logger log = LogManager.getLogger(HibernateUtilTest.class.getName());

    /**
     * check create Session
     * type: Positive
     */
    @Test
    void getSessionFactory() {
        log.debug("getSessionFactory [1]: test start");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        log.debug("getSessionFactory [2]: test end");

    }
    /**
     * check create Session
     * type: Negativity
     */
    @Test
    void getSessionFactoryNegativity() {
        log.debug("getSessionFactoryNegativity [1]: test start");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        assertNotNull(session);
        log.debug("getSessionFactoryNegativity [2]: test end");

    }
}