package ru.sfedu.assessmentHealth.api;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.exceptions.CsvException;
import jdk.jfr.StackTrace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.*;

import javax.print.Doc;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sfedu.assessmentHealth.utils.TestUtil.*;


@Tag("CREATE")
@Tag("INSERT")
@Tag("INSERT_NEG")
@Tag("SELECT")
@Tag("SELECT_NEG")
@Tag("DELETE")
@Tag("DELETE_NEG")
@Tag("Negative")
@Tag("Positive")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DataProviderCSVTest {
    final private String calcReportPath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_CALC_REPORT).concat(CONST.CSV_FILE_TYPE);
    final private String doctorPath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_DOCTOR).concat(CONST.CSV_FILE_TYPE);
    final private String patientPath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_PATIENT).concat(CONST.CSV_FILE_TYPE);
    final private String preparationPath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_PREPARATION).concat(CONST.CSV_FILE_TYPE);
    final private String schedulePath = CONST.CSV_PATH_FOLDER.concat(CONST.SQL_TABLE_NAME_SCHEDULE).concat(CONST.CSV_FILE_TYPE);


    private static final Logger log = LogManager.getLogger(DataProviderCSVTest.class.getName());
    DataProviderCSV providerCSV;
    {
        try {
            providerCSV = new DataProviderCSV();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void test1(){

    }

    @Test
    @Tag("INSERT")
    void insertDataDoctor() throws FileNotFoundException {
        log.debug("insertDataDoctor [1]: - start working test ");
        Doctor expected = createDoctor();
        StatusMethod result = providerCSV.InsertDataDoctor(expected);
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataDoctor [2]: - working end succeeded status {}",result);
    }

    @Test
    @Tag("INSERT")
    @Order(1)
    void insertDataPatient() {
        log.debug("insertDataPatient [1]: - start working test ");
        Patient expected = createPatient();
        StatusMethod result = providerCSV.InsertDataPatient(expected);
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataPatient [2]: - working end succeeded status {}",result);
    }

    @Test
    @Tag("INSERT")
    @Order(2)
    void insertDataPreparation() {
        log.debug("insertDataPreparation [1]: - start working test ");
        Preparation expected = createPreparation();
        StatusMethod result = providerCSV.InsertDataPreparation(expected,1,"2023-12-3");
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataPreparation [2]: - working end succeeded status {}",result);
    }

    @Test
    @Order(3)
    @Tag("INSERT_NEG")
    void insertDataPreparationNegativity() {
        log.debug("insertDataPreparationNegativity [1]: - start working test Negativity ");
        Preparation expected = createPreparation();
        StatusMethod result = providerCSV.InsertDataPreparation(expected,-1,"2023-12-3");
        assertEquals(result,StatusMethod.ERROR);
        log.debug("insertDataPreparationNegativity [2]: - working end succeeded Negativity status {}",result);

    }


    @Test
    @Tag("INSERT")
    @Order(4)
    void insertDataSchedule() {
        log.debug("insertDataSchedule [1]: - start working test Negativity ");
        Schedule expected = createSchedule();
        StatusMethod result = providerCSV.InsertDataSchedule(expected,1,"2023-12-3","12:23:23","13:00:00");
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataSchedule [2]: - working end succeeded  status {}",result);
    }
    @Test
    @Tag("INSERT_NEG")
    @Order(5)
    void insertDataScheduleNegativity() {
        log.debug("insertDataScheduleNegativity [1]: - start working test Negativity ");
        Schedule expected = createSchedule();
        StatusMethod result = providerCSV.InsertDataSchedule(expected,-1,"2023-12-3","12:23:23","13:00:00");
        assertEquals(result,StatusMethod.ERROR);
        log.debug("insertDataScheduleNegativity [2]: - working end succeeded  status {}",result);
    }

    @Test
    @Tag("INSERT")
    @Order(6)
    void insertDataCalcReport() {
        log.debug("insertDataCalcReport [1]: - start working test ");
        CalcReport expected = createCalcReport();
        StatusMethod result = providerCSV.InsertDataCalcReport(expected);
        assertEquals(result,StatusMethod.OK);
        log.debug("insertDataCalcReport [2]: - working end succeeded");
    }
    @Test
    @Tag("INSERT")
    @Order(7)
    void insertDataCalcReportNegativity() {
        log.debug("insertDataCalcReportNegativity [1]: - start working test ");
        CalcReport expected = createCalcReport();
        StatusMethod result = providerCSV.InsertDataCalcReport(expected);
        assertEquals(result,StatusMethod.ERROR);
        log.debug("insertDataCalcReportNegativity [2]: - working end succeeded");
    }


    @Test
    @Tag("SELECT")
    @Order(8)
    void selectDoctorId() {
        log.debug("selectDoctorId [1]: start working test ");
        assertEquals(providerCSV.selectDoctorId(1).get("Person_ID"),1);
        log.debug("selectDoctorId [2]: end working test ");
    }
    @Test
    @Tag("SELECT_NEG")
    @Order(9)
    void selectDoctorIdNegativity() {
        log.debug("selectDoctorIdNegativity [1]: start working test Negativity  ");
        assertNotEquals(providerCSV.selectDoctorId(1).get(CONST.KEY_PERSON),-1);
        log.debug("selectDoctorIdNegativity [2]: end working test Negativity");
    }



    @Test
    @Tag("SELECT")
    @Order(10)
    void selectPatientId() {
        log.debug("selectPatientId [1]: start working test");
        assertEquals(providerCSV.selectPatientId(1).get(CONST.KEY_PERSON),1);
        log.debug("selectPatientId [2]: end working test");
    }
    @Test
    @Tag("SELECT_NEG")
    @Order(11)
    void selectPatientIdNegativity() {
        log.debug("selectPatientId [1]: start working test Negativity");
        assertNotEquals(providerCSV.selectPatientId(1).get(CONST.KEY_PERSON),-1);
        log.debug("selectPatientId [2]: end working test Negativity");
    }

    @Test
    @Tag("SELECT")
    @Order(12)
    void selectPreparationId() {
        log.debug("selectPreparationId [1]: start working test");
        assertEquals(providerCSV.selectPreparationId(1).get(CONST.NAME_FIELD_PREPARATION_ID),1);
        log.debug("selectPreparationId [2]: end working test");
    }
    @Test
    @Tag("SELECT_NEG")
    @Order(13)
    void selectPreparationIdNegativity() {
        log.debug("selectPreparationIdNegativity [1]: start working test Negativity");
        assertNotEquals(providerCSV.selectPreparationId(1).get(CONST.NAME_FIELD_PREPARATION_ID),-1);
        log.debug("selectPreparationIdNegativity [2]: end working test Negativity");
    }

    @Test
    @Tag("SELECT")
    @Order(14)
    void selectScheduleId() {
        log.debug("selectScheduleId [1]: start working test ");
        assertEquals(providerCSV.selectScheduleId(1).get(CONST.NAME_FIELD_SCHEDULE_ID),1);
        log.debug("selectScheduleId [2]: end working test ");

    }
    @Test
    @Tag("SELECT_NEG")
    @Order(15)
    void selectScheduleIdNegativity() {
        log.debug("selectScheduleIdNegativity [1]: start working test Negativity");
        assertNotEquals(providerCSV.selectScheduleId(1).get(CONST.NAME_FIELD_SCHEDULE_ID),-1);
        log.debug("selectScheduleIdNegativity [2]: end working test Negativity");
    }
    @Test
    @Tag("SELECT")
    @Order(16)
    void selectCalcReportId() {
        log.debug("selectScheduleId [1]: start working test");
        assertEquals(providerCSV.selectCalcReportId(1).get(CONST.NAME_FIELD_REPORT_ID),1);
        log.debug("selectScheduleId [2]: end working test ");
    }
    @Test
    @Tag("SELECT_NEG")
    @Order(17)
    void selectCalcReportIdNegativity() {
        log.debug("selectCalcReportIdNegativity [1]: start working test");
        assertNotEquals(providerCSV.selectCalcReportId(1).get(CONST.NAME_FIELD_REPORT_ID),-1);
        log.debug("selectCalcReportIdNegativity [2]: end working test ");
    }

    @Test
    void deleteDataDoctor() {

        List<List<String>> s = providerCSV.generateListFromCsv(doctorPath);

            System.out.println( s.stream().toList());

//            reader2 = new CSVReader(new FileReader(doctorPath));
//            List<String[]> allElements = reader2.readAll();
//            allElements.stream().
//            System.out.println(allElements);
//            allElements.remove(0);
//            FileWriter sw = new FileWriter(doctorPath);
//            CSVWriter writer = new CSVWriter(sw);
//            writer.writeAll(allElements);
//            writer.close();


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
    @Test
    @Tag("Positive")
    void generateListFromCsvPositive(){
        log.debug("generateListFromCsvPositive [1]: - Positive test start");
       List<List<String>> actual =  providerCSV.generateListFromCsv(doctorPath);
       List<String> expected = new ArrayList<>(List.of("1","Artem", "Sim", "Evgeni", "20", "M", "DOCTOR", "3", "23.5", "Доктор", "Genekolog"));
       assertEquals(actual.get(0),expected);
       log.debug("generateListFromCsvPositive [2] - end working method");
    }
    @Test
    @Tag("Negative")
    void generateListFromCsvNegative(){
        log.debug("generateListFromCsvNegative [1]: - Negative test start");
        List<List<String>> actual =  providerCSV.generateListFromCsv(doctorPath);
        List<String> expected = new ArrayList<>(List.of("-1","Artem", "Sim", "Evgeni", "20", "M", "DOCTOR", "3", "23.5", "Доктор", "Genekolog"));
        assertNotEquals(actual.get(0),expected);
        log.debug("generateListFromCsvNegative [2] - end working method");

    }
//    @Test
//    void getDoctor(){
//        log.debug("getDoctor [1]: start working test ");
//        assertEquals(providerCSV.getDoctorCSV(1).get("Person_ID"),1);
//        log.debug("getDoctor [2]: end working test ");
//
//    }
}