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
    final private String calcReportPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_CALC_REPORT).concat(CONST.XML_FILE_TYPE);
    final private String doctorPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_DOCTOR).concat(CONST.XML_FILE_TYPE);
    final private String patientPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_PATIENT).concat(CONST.XML_FILE_TYPE);
    final private String preparationPath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_PREPARATION).concat(CONST.XML_FILE_TYPE);
    final private String schedulePath = CONST.XML_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_SCHEDULE).concat(CONST.XML_FILE_TYPE);

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
    void selectDoctorId() {
    }

    @Test
    void selectPatientId() {
    }

    @Test
    void selectPreparationId() {
    }

    @Test
    void selectScheduleId() {
    }

    @Test
    void selectCalcReportId() {
    }

    @Test
    void deleteDataDoctor() {
    }

    @Test
    void deleteDataPatient() {
    }

    @Test
    void deleteDataPreparation() {
    }

    @Test
    void deleteDataSchedule() {
    }

    @Test
    void deleteDataCalcReport() {
    }

}