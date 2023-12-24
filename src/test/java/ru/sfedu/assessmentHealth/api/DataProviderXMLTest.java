package ru.sfedu.assessmentHealth.api;
import static ru.sfedu.assessmentHealth.utils.XmlUtil.getXmlWrapper;
import com.sun.source.doctree.DocCommentTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.CalcReport;
import ru.sfedu.assessmentHealth.model.Doctor;
import ru.sfedu.assessmentHealth.model.StatusMethod;
import ru.sfedu.assessmentHealth.model.XmlWrapper;
import ru.sfedu.assessmentHealth.utils.XmlUtil;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sfedu.assessmentHealth.utils.TestUtil.*;

@Tag("CREATE")
@Tag("INSERT")
@Tag("INSERT_NEG")
@Tag("SELECT")
@Tag("SELECT_NEG")
@Tag("DELETE")
@Tag("DELETE_NEG")


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DataProviderXMLTest {

    private static final Logger log = LogManager.getLogger(DataProviderCSVTest.class.getName());
    DataProviderXML providerXML;

    {
        try {
            providerXML = new DataProviderXML();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @Tag("INSERT")
    @Order(1)
    void insertDataDoctorTest() throws Exception {
        log.debug("insertDataDoctorTest [1]: - Start method working insert test xml");
          StatusMethod result =  providerXML.InsertDataDoctor(createDoctor());
          assertEquals(result,StatusMethod.OK);
        log.debug("insertDataDoctorTest [2]: - END method working test end");
    }

    @Test
    @Tag("INSERT")
    @Order(2)
    void insertDataPatientTest() {
        log.debug("insertDataPatientTest [1]: - Start method working insert test xml");
        StatusMethod result = providerXML.InsertDataPatient(createPatient());
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataPatientTest [2]: - END method working insert test end xml ");
    }

    @Test
    @Tag("INSERT")
    @Order(3)
    void insertDataPreparationTest() {
        log.debug("insertDataPreparationTest [1]: - Start method working insert test xml");
        System.out.println(createPreparation());
        StatusMethod result = providerXML.InsertDataPreparation(
                createPreparation(),
                1,
                "2023-12-3");
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataPreparationTest [2]: - END method working insert test end xml ");
    }

    @Test
    @Tag("INSERT_NEG")
    @Order(4)
    void insertDataPreparationNegative() {
        log.debug("insertDataPreparationNegative [1]: - Start method working insert test xml");
        StatusMethod result = providerXML.InsertDataPreparation(
                createPreparation(),
                -1,
                "2023-12-3");
        assertNotEquals(result,StatusMethod.OK);
        log.debug("insertDataPreparationNegative [2]: - END method working insert test end xml ");

    }

    @Test
    @Tag("INSERT")
    @Order(5)
    void insertDataSchedule() {
        log.debug("insertDataSchedule [1]: - Start method working insert test xml");
        StatusMethod result = providerXML.InsertDataSchedule(
                createSchedule(),
                1,
                "2023-12-3",
                "12:23:23",
                "13:00:00");
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataSchedule [2]: - END method working insert test end xml ");

    }

    @Test
    @Tag("INSERT_NEG")
    @Order(6)
    void insertDataScheduleNegative() {
        log.debug("insertDataScheduleNegative [1]: - Start method working insert test xml");
        StatusMethod result = providerXML.InsertDataSchedule(
                createSchedule(),
                -1,
                "2023-12-3",
                "12:23:23",
                "13:00:00");
        assertNotEquals(result,StatusMethod.OK);
        log.debug("insertDataScheduleNegative [2]: - END method working insert test end xml ");


    }
    @Test
    @Tag("INSERT")
    @Order(7)
    void insertDataCalcReport() {
        log.debug("insertDataCalcReport [1]: - Start method working insert test xml");
        StatusMethod result = providerXML.InsertDataCalcReport(createCalcReport());
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataCalcReport [2]: - END method working insert test end xml ");
    }

    @Test
    @Tag("INSERT_NEG")
    @Order(8)
    void insertDataCalcReportNegativity() {
        log.debug("insertDataCalcReportNegativity [1]: - Start method working insert test xml");
        CalcReport calcReport = createCalcReport();
        calcReport.setFkToPatient(-1);
        calcReport.setFkToDoctor(-1);
        StatusMethod result = providerXML.InsertDataCalcReport(calcReport);
        assertNotEquals(result,StatusMethod.OK);
        log.debug("insertDataCalcReportNegativity [2]: - END method working insert test end xml ");
    }
    @Test
    @Tag("SELECT")
    @Order(9)
    void selectDoctorId() {
        log.debug("selectDoctorId [1]: - Start method working select test xml");
        Map<String,Object> actual =providerXML.selectDoctorId(1);
        assertEquals(actual.get(CONST.KEY_PERSON),1);
        log.debug("selectDoctorId [2]: - END method working select test xml {}",actual);
    }
    @Test
    @Tag("SELECT_NEG")
    @Order(10)
    void selectDoctorIdNegativity() {
        log.debug("selectDoctorIdNegativity [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectDoctorId(-1);
        assertEquals(actual.get(CONST.KEY_PERSON),null);
        log.debug("selectDoctorIdNegativity [2]: - END method working select test xml {}",actual);
    }

    @Test
    @Tag("SELECT")
    @Order(11)
    void selectPatientId() {
        log.debug("selectPatientId [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectPatientId(1);
        assertEquals(actual.get(CONST.KEY_PERSON),1);
        log.debug("selectPatientId [2]: - END method working select test xml {}",actual);
    }

    @Test
    @Tag("SELECT_NEG")
    @Order(12)
    void selectPatientIdNegativity() {
        log.debug("selectPatientIdNegativity [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectPatientId(-1);
        assertEquals(actual.get(CONST.KEY_PERSON),null);
        log.debug("selectPatientIdNegativity [2]: - END method working select test xml {}",actual);

    }
    @Test
    @Tag("SELECT")
    @Order(13)
    void selectPreparationId() {
        log.debug("selectPreparationId [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectPreparationId(1);
        assertEquals(actual.get(CONST.KEY_PREPARATION),1);
        log.debug("selectPreparationId [2]: - END method working select test xml {}",actual);

    }
    @Test
    @Tag("SELECT_NEG")
    @Order(13)
    void selectPreparationIdNegativity() {
        log.debug("selectPreparationIdNegativity [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectPreparationId(-1);
        assertEquals(actual.get(CONST.KEY_PREPARATION),null);
        log.debug("selectPreparationIdNegativity [2]: - END method working select test xml {}",actual);

    }

    @Test
    @Tag("SELECT")
    @Order(14)
    void selectScheduleId() {
        log.debug("selectScheduleId [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectScheduleId(1);
        assertEquals(actual.get(CONST.KEY_SCHEDULE),1);
        log.debug("selectScheduleId [2]: - END method working select test xml {}",actual);

    }

    @Test
    @Tag("SELECT_NEG")
    @Order(15)
    void selectScheduleIdNegativity() {
        log.debug("selectScheduleIdNegativity [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectScheduleId(-1);
        assertEquals(actual.get(CONST.KEY_SCHEDULE),null);
        log.debug("selectScheduleIdNegativity [2]: - END method working select test xml {}",actual);

    }
    @Test
    @Tag("SELECT")
    @Order(16)
    void selectCalcReportId() {
        log.debug("selectCalcReportId [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectCalcReportId(1);
        assertEquals(actual.get(CONST.KEY_CALC_REPORT),1);
        log.debug("selectCalcReportId [2]: - END method working select test xml {}",actual);

    }
    @Test
    @Tag("SELECT_NEG")
    @Order(17)
    void selectCalcReportIdNegativity() {
        log.debug("selectCalcReportIdNegativity [1]: - Start method working select test xml");
        Map<String,Object> actual = providerXML.selectCalcReportId(-1);
        assertEquals(actual.get(CONST.KEY_CALC_REPORT),null);
        log.debug("selectCalcReportIdNegativity [2]: - END method working select test xml {}",actual);

    }

    @Test
    @Tag("DELETE")
    @Order(18)
    void deleteDataDoctor() {
        log.debug("deleteDataDoctor [1]: - Start method working delete test xml");
        StatusMethod result_del =  providerXML.deleteDataDoctor(1);
        Map<String,Object> result_select = providerXML.selectDoctorId(1);
        assertTrue(result_select.isEmpty());
        log.debug("deleteDataDoctor [2]: - END method working delete test xml {}",result_del);
    }
    @Test
    @Tag("DELETE_NEG")
    @Order(19)
    void deleteDataDoctorNegativity() {
        log.debug("deleteDataDoctorNegativity [1]: - Start method working delete test xml");
        StatusMethod result_del =  providerXML.deleteDataDoctor(-1);
        assertEquals(result_del,StatusMethod.ERROR);
        log.debug("deleteDataDoctorNegativity [2]: - END method working delete test xml {}",result_del);

    }

    @Test
    @Tag("DELETE")
    @Order(20)
    void deleteDataPatient() {
        log.debug("deleteDataPatient [1]: - Start method working delete test xml");
        StatusMethod result_del =  providerXML.deleteDataPatient(1);
        Map<String,Object> result_select = providerXML.selectPatientId(1);
        assertTrue(result_select.isEmpty());
        log.debug("deleteDataPatient [2]: - END method working delete test xml {}",result_del);

    }

    @Test
    @Tag("DELETE_NEG")
    @Order(21)
    void deleteDataPatientNegativity() {
        log.debug("deleteDataPatientNegativity [1]: - Start method working delete test xml");
        StatusMethod result_del =  providerXML.deleteDataPatient(-1);
        assertEquals(result_del,StatusMethod.ERROR);
        log.debug("deleteDataPatientNegativity [2]: - END method working delete test xml {}",result_del);
    }
    @Test
    @Tag("DELETE")
    @Order(22)
    void deleteDataPreparation() {
        log.debug("deleteDataPreparation [1]: - Start method working delete test xml");
        StatusMethod result_del =  providerXML.deleteDataPreparation(1);
        Map<String,Object> result_select = providerXML.selectPreparationId(1);
        assertTrue(result_select.isEmpty());
        log.debug("deleteDataPreparation [2]: - END method working delete test xml {}",result_del);

    }
    @Test
    @Tag("DELETE_NEG")
    @Order(23)
    void deleteDataPreparationNegativity() {
        log.debug("deleteDataPreparationNegativity [1]: - Start method working delete test xml");
        StatusMethod result_del =  providerXML.deleteDataPreparation(-1);
        assertEquals(result_del,StatusMethod.ERROR);
        log.debug("deleteDataPreparationNegativity [2]: - END method working delete test xml {}",result_del);

    }

    @Test
    @Tag("DELETE")
    @Order(24)
    void deleteDataSchedule() {
        log.debug("deleteDataSchedule [1]: - Start method working delete test xml");
        StatusMethod result_del =  providerXML.deleteDataSchedule(1);
        Map<String,Object> result_select = providerXML.selectScheduleId(1);
        assertTrue(result_select.isEmpty());
        log.debug("deleteDataSchedule [2]: - END method working delete test xml {}",result_del);

    }

    @Test
    @Tag("DELETE")
    @Order(25)
    void deleteDataCalcReport() {
        log.debug("deleteDataCalcReport [1]: - Start method working delete test xml");
        StatusMethod result_del =  providerXML.deleteDataCalcReport(1);
        Map<String,Object> result_select = providerXML.selectCalcReportId(1);
        assertTrue(result_select.isEmpty());
        log.debug("deleteDataCalcReport [2]: - END method working delete test xml {}",result_del);

    }

}