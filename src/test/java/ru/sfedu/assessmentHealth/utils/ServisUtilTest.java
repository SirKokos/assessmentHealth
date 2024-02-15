package ru.sfedu.assessmentHealth.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.Main;
import ru.sfedu.assessmentHealth.api.BaseTest;
import ru.sfedu.assessmentHealth.api.DataProviderCsv;
import ru.sfedu.assessmentHealth.api.IDataProvider;
import ru.sfedu.assessmentHealth.model.Patient;
import ru.sfedu.assessmentHealth.model.Preparation;
import ru.sfedu.assessmentHealth.model.Schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sfedu.assessmentHealth.utils.ServisUtil.rangeCheckerValuesAssessmentHealth;

class ServisUtilTest extends CreateObjTest{
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

    @Test
    void rangeCheckerValuesAssessmentHealthTest() {
        log.debug("rangeCheckerValuesAssessmentHealthTest [1]: start working");
        Integer health = 0;
        Map<String,Integer> result = new HashMap<>();
        Patient patient = getPatient();
        Double redBloodCellsCount = patient.getCellsBlood();
        Double hemoglobinLevel = patient.getHemoglobin();

        Pair<Map<String, Integer>, Integer> actualRedBloodCellsCount = rangeCheckerValuesAssessmentHealth(result,
                Const.VALID_CELLS_BLOOD,
                redBloodCellsCount,
                Const.POINT_BLOOD_HEALTH,
                Const.RESULT_CELLS_BLOOD);

        Pair<Map<String, Integer>, Integer> actualHemoglobinLevel = rangeCheckerValuesAssessmentHealth(actualRedBloodCellsCount.getLeft(),
                Const.VALID_HEMOGLOBIN_M,
                hemoglobinLevel,
                Const.POINT_HEMOGLOBIN_HEALTH,
                Const.RESULT_HEMOGLOBIN);

        assertEquals(actualRedBloodCellsCount.getRight(),0);
        assertEquals(actualHemoglobinLevel.getRight(),0);
        log.debug("rangeCheckerValuesAssessmentHealthTest [2]: end working \n actualRedBloodCellsCount = {} \n actualHemoglobinLevel = {}",actualRedBloodCellsCount,actualHemoglobinLevel);
    }
    @Test
    void rangeCheckerValuesAssessmentHealthTestNegativity() {
        log.debug("rangeCheckerValuesAssessmentHealthTestNegativity [1]: start working");
        Integer health = 0;
        Map<String,Integer> result = new HashMap<>();
        Patient patient = getPatient();
        Double redBloodCellsCount = patient.getCellsBlood();
        Double hemoglobinLevel = patient.getHemoglobin();

        Pair<Map<String, Integer>, Integer> actualRedBloodCellsCount = rangeCheckerValuesAssessmentHealth(result,
                Const.VALID_CELLS_BLOOD,
                redBloodCellsCount,
                Const.POINT_BLOOD_HEALTH,
                Const.RESULT_CELLS_BLOOD);

        Pair<Map<String, Integer>, Integer> actualHemoglobinLevel = rangeCheckerValuesAssessmentHealth(actualRedBloodCellsCount.getLeft(),
                Const.VALID_HEMOGLOBIN_M,
                hemoglobinLevel,
                Const.POINT_HEMOGLOBIN_HEALTH,
                Const.RESULT_HEMOGLOBIN);

        assertFalse(actualRedBloodCellsCount.getLeft().isEmpty());
        assertFalse(actualHemoglobinLevel.getLeft().isEmpty());
        log.debug("rangeCheckerValuesAssessmentHealthTestNegativity [2]: end working \n actualRedBloodCellsCount = {} \n actualHemoglobinLevel = {}",actualRedBloodCellsCount,actualHemoglobinLevel);



    }
}