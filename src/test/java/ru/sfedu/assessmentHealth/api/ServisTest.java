package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServisTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(ServisTest.class.getName());

    Servis servis = new Servis();
    @Test
    void assessmentHealth() {
        log.debug("assessmentHealth [1]: Start working Test");
        Map<String, Integer> actual = servis.assessmentHealth(getPatient());
        assertEquals(actual.get(Const.RESULT_HEALTH),55);
        log.debug("assessmentHealth [2]: end working Test");
    }
    @Test
    void assessmentHealthNegativity() {
        log.debug("assessmentHealthNegativity [1]: Start working Test");
        Map<String, Integer> actual = servis.assessmentHealth(getPatient());
        assertNotNull(actual);
        log.debug("assessmentHealthNegativity [2]: end working Test");
    }

    @Test
    void heallingRecom() {
        log.debug("heallingRecom [1]: Start working Test");
        Map<String, Integer> pat = servis.assessmentHealth(getPatient());
        List<String> actual = servis.heallingRecom(pat);
        assertEquals(actual,Const.TEST_HEALTH_RECOM);
        log.debug("heallingRecom [2]: end working Test");
    }
    @Test
    void heallingRecomNegativity() {
        log.debug("heallingRecomNegativity [1]: Start working Test");
        Map<String, Integer> pat = servis.assessmentHealth(getPatient());
        List<String> actual = servis.heallingRecom(pat);
        assertNotNull(actual);
        log.debug("heallingRecomNegativity [2]: end working Test");
    }

    @Test
    void determinationUrgency() {
        IDataProvider dataProvider = new DataProviderCsv();
        Map<String, Integer> map = servis.assessmentHealth(getPatient());
        servis.determinationUrgency(map,dataProvider);
    }
}