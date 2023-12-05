package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

class DataProviderPostgresTest {
    public static final Logger log = LogManager.getLogger(DataProviderPostgresTest.class.getName());

    DataProviderPostgres dataProviderPostgres;



    {
        try {
            dataProviderPostgres = new DataProviderPostgres();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    Connection getConnection(){
        String url = CONST.BD_POSTGRES_HOST.concat(CONST.BD_POSTGRES_NAME);
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, CONST.BD_POSTGRES_USER, CONST.BD_POSTGRES_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    Doctor createDoctor(){
        Doctor doctorTest = new Doctor();
        doctorTest.setPerson_ID(1);
        doctorTest.setName("Artem");
        doctorTest.setSurname("Sim");
        doctorTest.setSecondName("Evgeni");
        doctorTest.setAge(20);
        doctorTest.setGender('M');
        doctorTest.setExperience(3);
        doctorTest.setAvgPatient(23.5);
        doctorTest.setQualification("Доктор");
        doctorTest.setSpecialization("Genekolog");
        doctorTest.setStatus(StatusPerson.DOCTOR);

        return doctorTest;
    }

    Patient createPatient(){
        Patient patientTest = new Patient();
        patientTest.setPerson_ID(1);
        patientTest.setName("Artem_2");
        patientTest.setSurname("Sim");
        patientTest.setSecondName("Evgeni");
        patientTest.setAge(20);
        patientTest.setGender('M');
        patientTest.setCellsBlood(1.5);
        patientTest.setHemoglobin(1.4);
        patientTest.setPlatelets(12.3);
        patientTest.setTestosterone(12.31);
        patientTest.setGlucose(12.23);
        patientTest.setCholesterol(12.23);
        patientTest.setArterialPress((short) 103);
        patientTest.setStatus(StatusPerson.PATIENT);
        patientTest.setStatusVisit(StatusVisit.IN);

        return patientTest;
    }

    Preparation createPreparation(){
        Preparation prep = new Preparation();
        prep.setPreparation_ID(1);
        prep.setFkPreparationToDoctor(1);
        prep.setNamePrep("ddw");
        prep.setDosage(12.123);
        prep.setStatusVisitPreparation(StatusVisitPreparation.LOW);
        prep.setAboutPrep("dwdqdwdqdqdqdw");
        prep.setCountPrep(12);
        return prep;
    }

    Schedule createSchedule(){
        Schedule schedule = new Schedule();
        schedule.setScheduleID(1);
        schedule.setFkToDoctor(1);
        schedule.setDateWeek(DateWeek.MONDAY);
        schedule.setStatusSchedule(StatusSchedule.PROCESS);
        return  schedule;
    }

    CalcReport createCalcReport(){
        CalcReport calcReport = new CalcReport();
        calcReport.setReport_ID(1);
        calcReport.setFkToDoctor(1);
        calcReport.setFkToPatient(1);
        calcReport.setNameDoctor("Artem");
        calcReport.setPatientName("Artem_2");
        calcReport.setBloodAnalyse(true);
        calcReport.setGlucoseAnalyse(true);
        calcReport.setHormoneAnalyse(true);
        calcReport.setArterialAnalyse(true);
        calcReport.setPrice(123.4);
        return calcReport;
    }

    @Test
    void conect(){
        DataProviderPostgres p = null;
            try {
                p = new DataProviderPostgres();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                System.out.println(p);
            }
    }

    @Test
    void createTables() {
        log.info("createTables [1]: - test started");
        List<String> testTab = new ArrayList<>();
        testTab.add("calcreport");
        testTab.add("doctor");
        testTab.add("patient");
        testTab.add("preparation");
        testTab.add("schedule");

        try {
            Connection connection = getConnection();
            ResultSet resultSet = connection.getMetaData().getTables(null,null,"%", new String[]{"TABLE"});
            List<String> nameTabTest = new ArrayList<>();
            while (resultSet.next()) {
                nameTabTest.add(resultSet.getString("TABLE_NAME"));
            }
            log.info("nameTabTest [2]: actual data = {}",testTab);
            log.info("testTab [3]: expected  data = {}",nameTabTest);
             assertIterableEquals(testTab,nameTabTest);
            log.info("createTables [4]: - test succeeded");
        } catch (SQLException e) {
            log.error("createTables() [5]: - error {}",e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Test
    void insertDataDoctor() {
        log.debug("insertDataDoctor [1]: - insert Data Doctor test started Postgres");
        try {
            dataProviderPostgres.InsertDataDoctor(createDoctor());
        }catch (Exception e){
            log.error("InsertDataDoctor [2]: - error TEST {}", e.getMessage());
        }
        log.debug("insertDataDoctor [3]: - insert Data Doctor test succeeded Postgres");
    }

    @Test
    void insertDataPatient() {
        log.debug("insertDataPatient [1]: - insert Data Patient test started Postgres");
        try {
            dataProviderPostgres.InsertDataPatient(createPatient());
        }catch (Exception e){
            log.error("insertDataPatient [2]: - error TEST {}", e.getMessage());
        }

        log.debug("insertDataPatient [3]: - insert Data Patient test succeeded Postgres");

    }

    @Test
    void insertDataPreparation() {
        log.debug("insertDataPreparation [1]: - insert Data Preparation test started Postgres");
        try {
            dataProviderPostgres.InsertDataPreparation(createPreparation(),1,"2023-12-3");
        }catch (Exception e){
            log.error("insertDataPatient [2]: - error TEST {}", e.getMessage());
        }
        log.debug("insertDataPatient [3]: - insert Data Patient test succeeded Postgres");


    }

    @Test
    void insertDataSchedule() {
        log.debug("insertDataSchedule [1]: - insert Data Schedule test started Postgres");
        try {
            dataProviderPostgres.InsertDataSchedule(createSchedule(),"2023-12-3","12:23:23","13:00:00");
        }catch (Exception e){
            log.error("insertDataPatient [2]: - error TEST {}", e.getMessage());
        }
        log.debug("insertDataPatient [3]: - insert Data Patient test succeeded Postgres");
    }

    @Test
    void insertDataCalcReport() {
        log.debug("insertDataCalcReport[1]: - insert Data Calc Report test started Postgres");
        try {
            dataProviderPostgres.InsertDataCalcReport(createCalcReport());
        }catch (Exception e){
            log.error("insertDataCalcReport [2]: - error TEST {}", e.getMessage());
        }
        log.debug("insertDataCalcReport [3]: - insert Data Calc Report test succeeded Postgres");
    }


    void checkActualExpectedPerson(Map<String,Object> actual, Map<String, Object> expected){
        log.debug("checkActualExpectedPerson [1]: started checking object ");
        assertEquals(actual.get("status").toString() ,expected.get("Status").toString());
        assertEquals(actual.get("surname") ,expected.get("Surname"));
        assertEquals(actual.get("name") ,expected.get("Name"));
        log.debug("checkActualExpectedPerson [2]: succeeded");

    }

    @Test
    void selectDoctorId() {
        log.debug("selectDoctorId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createDoctor());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectDoctorId(1);
        log.info("selectDoctorId [2]: actual data = {}", actual);
        log.info("selectDoctorId [3]: expected data = {}", expected);

        assertEquals(actual.get("doctor_id") ,expected.get("Person_ID"));

        checkActualExpectedPerson(actual,expected);

        log.debug("selectDoctorId [2]: TEST select data succeeded ");
    }

    @Test
    void selectPatientId() {
        log.debug("selectPatientId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createPatient());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectPatientId(1);
        log.info("selectPatientId [2]: actual data = {}", actual);
        log.info("selectPatientId [3]: expected data = {}", expected);

        assertEquals(actual.get("patient_id") ,expected.get("Person_ID"));

        checkActualExpectedPerson(actual,expected);

    }


    @Test
    void selectPreparationId() {
        log.debug("selectPreparationId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createPreparation());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectPreparationId(1);

        log.info("selectPatientId [2]: actual data = {}", actual);
        log.info("selectPatientId [3]: expected data = {}", expected);

        assertEquals(actual.get("preparation_id") ,expected.get("Preparation_ID"));
        assertEquals(actual.get("nameprep") ,expected.get("NamePrep"));
        log.debug("selectPreparationId [4]: TEST select data succeeded ");
    }

    @Test
    void selectScheduleId() {
        log.debug("selectScheduleId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createSchedule());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectScheduleId(1);

        log.info("selectScheduleId [2]: actual data = {}", actual);
        log.info("selectScheduleId [3]: expected data = {}", expected);

        assertEquals(actual.get("schedule_id") ,expected.get("Schedule_ID"));
        assertEquals(actual.get("statusschedule") ,expected.get("statusSchedule").toString());

        log.debug("selectScheduleId [4]: TEST select data succeeded ");
    }

    @Test
    void selectCalcReportId() {
        log.debug("selectCalcReportId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createCalcReport());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectCalcReportId(1);

        log.info("selectCalcReportId [2]: actual data = {}", actual);
        log.info("selectCalcReportId [3]: expected data = {}", expected);

        assertEquals(actual.get("report_id") ,expected.get("Report_ID"));
        assertEquals(actual.get("patientname") ,expected.get("PatientName"));
        assertEquals(actual.get("namedoctor") ,expected.get("NameDoctor"));

        log.debug("selectScheduleId [4]: TEST select data succeeded ");
    }
}