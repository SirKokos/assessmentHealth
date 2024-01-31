package ru.sfedu.assessmentHealth.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Main;
import ru.sfedu.assessmentHealth.api.DataProviderCsv;
import ru.sfedu.assessmentHealth.api.IDataProvider;
import ru.sfedu.assessmentHealth.model.Preparation;
import ru.sfedu.assessmentHealth.model.Schedule;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServisUtilTest {
    private static final Logger log = LogManager.getLogger(ServisUtilTest.class.getName());

    @Test
    void getListSchedule() {
        log.debug("getListSchedule [1]: start working");
        IDataProvider DataProviderCsv = new DataProviderCsv();
       List<Schedule> actual = ServisUtil.getListSchedule(DataProviderCsv,"1 2");
       assertTrue(actual.size()>0);
        log.debug("getListSchedule [2]: {}", actual);
    }
    @Test
    void getListScheduleNegativity() {
        log.debug("getListScheduleNegativity [1]: start working");
        IDataProvider DataProviderCsv = new DataProviderCsv();
        List<Schedule> actual = ServisUtil.getListSchedule(DataProviderCsv," ");
        assertFalse(actual.size()>0);
        log.debug("getListScheduleNegativity [2]: {}", actual);
    }

    @Test
    void getListPreparation() {
        log.debug("getListPreparation [1]: start working");
        IDataProvider DataProviderCsv = new DataProviderCsv();
        List<Preparation> actual = ServisUtil.getListPreparation(DataProviderCsv,"1 2");
        assertTrue(actual.size()>0);
        log.debug("getListPreparation [2]: {}", actual);
    }
    @Test
    void getListPreparationNegativity() {
        log.debug("getListPreparationNegativity [1]: start working");
        IDataProvider DataProviderCsv = new DataProviderCsv();
        List<Preparation> actual = ServisUtil.getListPreparation(DataProviderCsv," ");
        assertFalse(actual.size()>0);
        log.debug("getListPreparationNegativity [2]: {}", actual);
    }
}