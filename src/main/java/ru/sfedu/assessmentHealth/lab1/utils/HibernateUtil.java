package ru.sfedu.assessmentHealth.lab1.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.assessmentHealth.lab1.api.HibernateDataProviderPostgres;

/**
        *
        * @author 79897
        */
public class HibernateUtil {

    private static final Logger log = LogManager.getLogger(HibernateUtil.class.getName());
    private static SessionFactory sessionFactory;

    /**
     * Method generate Session from config
     * @return Session
     */
    public static SessionFactory getSessionFactory(){
        log.debug("getSessionFactory [1]: start session factory");

        try {
            System.out.print(sessionFactory == null);
            if(sessionFactory == null){
                // loads configuration and mappings
                Configuration configuration = new Configuration().configure();
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                MetadataSources metadataSources = new MetadataSources(serviceRegistry);
                sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
            }
        } catch (HibernateException e) {
            log.error("getSessionFactory [2]: error {}",e.getMessage());
        }
        log.debug("getSessionFactory [3]: end working");
        return sessionFactory;

    }
}