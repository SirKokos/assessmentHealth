package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.CalcReport;
import ru.sfedu.assessmentHealth.model.Schedule;
import ru.sfedu.assessmentHealth.model.StatusAnswer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ServisTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(ServisTest.class.getName());

    Servis servis = new Servis();
    @Test
    void assessmentHealth() {
        log.debug("assessmentHealth [1]: Start working Test");
        Map<String, Integer> actual = servis.assessmentHealth(getPatient());
        assertEquals(actual.get(Const.RESULT_HEALTH),Const.TEST_DATA_ASSESSMENT_HEALTH);
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
        log.debug("determinationUrgency [1]: Start working Test");
        IDataProvider dataProvider = new DataProviderCsv();
        Map<String, Integer> map = servis.assessmentHealth(getPatient());
        Map<Integer, List<Schedule>> test = servis.determinationUrgency(map, dataProvider);
        assertNotNull(test);
        log.debug("determinationUrgency [2]: end working Test");
    }





    @Test
    void visitDoctor() {
        log.debug("visitDoctor [1]: Start working Test");
        StatusAnswer actual =  servis.visitDoctor(getPatient());
        assertEquals(actual,StatusAnswer.OK);
        log.debug("visitDoctor [2]: end working Test");
    }
    @Test
    void visitDoctorNegativity() {
        log.debug("visitDoctor [1]: Start working Test");
        StatusAnswer actual =  servis.visitDoctor(getPatient());
        assertNotEquals(actual,StatusAnswer.ERROR);
        log.debug("visitDoctor [2]: end working Test");
    }

    @Test
    void arivialDoctor() {
        log.debug("arivialDoctor [1]: Start working Test");
        IDataProvider dataProvider = new DataProviderCsv();
        StatusAnswer actual = servis.arivialDoctor(getPatient(),dataProvider);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("arivialDoctor [2]: end working Test");
    }
    @Test
    void arivialDoctorNegativity() {
        log.debug("arivialDoctorNegativity [1]: Start working Test");
        IDataProvider dataProvider = new DataProviderCsv();
        StatusAnswer actual = servis.arivialDoctor(getPatient(),dataProvider);
        assertNotEquals(actual,StatusAnswer.ERROR);
        log.debug("arivialDoctorNegativity [2]: end working Test");
    }

    @Test
    void calcExpDoctor() {
        log.debug("calcExpDoctor [1]: Start working Test");
        Double actual =  servis.calcExpDoctor(getDoctor());
        assertTrue(actual>0);
        log.debug("calcExpDoctor [2] end working {}",actual);
    }
    @Test
    void calcExpDoctorNegativity() {
        log.debug("calcExpDoctorNegativity [1]: Start working Test");
        Double actual =  servis.calcExpDoctor(getDoctor());
        assertNotEquals(actual,null);
        log.debug("calcExpDoctorNegativity [2] end working {}",actual);
    }


    @Test
    void totalReport() {
        log.debug("totalReport [1]: Start working Test");
        Optional<CalcReport> actual =  servis.TotalReport(getDoctor(),getPatient());
        assertEquals(actual.get().getPatient(),getCalcReport().getPatient());
        log.debug("totalReport [2] end working {}",actual);
    }
    @Test
    void totalReportNegativity() {
        log.debug("totalReportNegativity [1]: Start working Test");
        Optional<CalcReport> actual =  servis.TotalReport(getDoctor(),getPatient());
        assertNotNull(actual.get());
        log.debug("totalReportNegativity [2] end working {}",actual);
    }

    @Test
    void calculatePrice() {
        log.debug("calculatePrice [1]: Start working Test");
        Optional<CalcReport> actual =  servis.calculatePrice(getPatient(),getDoctor(),true);
        System.out.println(actual.get());
        assertEquals(actual.get().getPrice(),Const.TEST_DATA_PRICE_CALCULATE_PRICE);
        log.debug("calculatePrice [2] end working {}",actual);
    }
    @Test
    void calculatePriceNegativity() {
        log.debug("calculatePrice [1]: Start working Test");
        Optional<CalcReport> actual =  servis.calculatePrice(getPatient(),getDoctor(),true);
        assertNotNull(actual);
        log.debug("calculatePrice [2] end working {}",actual);
    }
}