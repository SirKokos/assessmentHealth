package ru.sfedu.assessmentHealth.lab3.MappedSuperclass.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.lab1.api.HibernateDataProviderPostgres;
import ru.sfedu.assessmentHealth.lab2.api.HibernateDataProviderLab2;

public class HibernateDataProviderLab3 extends HibernateDataProviderPostgres {
    private static final Logger log = LogManager.getLogger(HibernateDataProviderLab3.class.getName());


}
