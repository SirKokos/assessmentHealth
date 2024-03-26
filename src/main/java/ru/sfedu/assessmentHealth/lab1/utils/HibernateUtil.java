package ru.sfedu.assessmentHealth.lab1.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.io.File;
import ru.sfedu.assessmentHealth.lab1.api.HibernateDataProviderPostgres;
import ru.sfedu.assessmentHealth.lab2.model.Person;
import ru.sfedu.assessmentHealth.lab2.model.TestEntity;

/**
        *
        * @author 79897
        */
public class HibernateUtil {

    private static final Logger log = LogManager.getLogger(HibernateUtil.class.getName());
    private static String pathConfig;
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    public static String getPathConfig() {
        return pathConfig;
    }

    public static void setPathConfig(String pathConfig) {
        HibernateUtil.pathConfig = pathConfig;
    }

    /**
     * Method generate Session from config
     * @return Session
     */
    public static SessionFactory getSessionFactory(){
        log.debug("getSessionFactory [1]: start session factory");

        try {
            if(sessionFactory == null){
                getConfiguration();

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                MetadataSources metadataSources = new MetadataSources(serviceRegistry);
                metadataSources.addAnnotatedClass(TestEntity.class);
                metadataSources.addAnnotatedClass(Person.class);
                sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

            }
        } catch (HibernateException e) {
            log.error("getSessionFactory [2]: error {}",e.getMessage());
        }
        log.debug("getSessionFactory [3]: end working");
        return sessionFactory;

    }

    /**
     * Method set configuration default or custom
     */
    private static void getConfiguration(){
        log.debug("getConfiguration [1]: start getConfiguration");
        try {
            if (pathConfig != null) {
                log.debug("getConfiguration [2]: custom configuration file");
                File file = new File(pathConfig);
                configuration = new Configuration().configure(file);
            } else {
                log.debug("getConfiguration [3]: default configuration file");
                configuration = new Configuration().configure();
            }
        }catch (Exception e){
            log.error("getConfiguration [4]: ERROR {}", e.getMessage());
        }
        log.debug("getConfiguration [5]: end working");
    }
}