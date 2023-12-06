package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.assessmentHealth.CONST;
import ru.sfedu.assessmentHealth.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Tag("CREATE")
@Tag("INSERT")
@Tag("INSERT_NEG")
@Tag("SELECT")
@Tag("SELECT_NEG")
@Tag("DELETE")
@Tag("DELETE_NEG")


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Tag("CREATE")
    @Order(1)
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
    @Tag("INSERT")
    @Order(2)
    void insertDataDoctor() {
        log.debug("insertDataDoctor [1]: - insert Data Doctor test started Postgres");
        Doctor expected = createDoctor();
        try {
            dataProviderPostgres.InsertDataDoctor(expected);
        }catch (Exception e){
            log.error("InsertDataDoctor [2]: - error TEST {}", e.getMessage());
        }
        Map<String,Object> actual = dataProviderPostgres.selectDoctorId(expected.getPersonID());

        assertEquals(actual.get("doctor_id"),expected.getPersonID());
        log.debug("insertDataDoctor [3]: - insert Data Doctor test succeeded Postgres");
    }


    @Test
    @Tag("INSERT_NEG")
    @Order(3)
    void insertDataDoctorNegative() {
        log.debug("insertDataDoctorNegative [1]: - insert Data Doctor test started Postgres");
        Doctor expected = createDoctor();
        Map<String,Object> actual = null;
        try {
            actual = dataProviderPostgres.selectDoctorId(expected.getPersonID());
        }catch (Exception e){
            log.error("insertDataDoctorNegative [2]: - error TEST {}", e.getMessage());
        }

        assertFalse( expected.getPersonID() != actual.get("doctor_id"));
        log.debug("insertDataDoctorNegative [3]: - insert Data Doctor test Negative succeeded Postgres");
    }



    @Test
    @Tag("INSERT")
    @Order(4)
    void insertDataPatient() {
        log.debug("insertDataPatient [1]: - insert Data Patient test started Postgres");
        Patient expected = createPatient();
        try {
            dataProviderPostgres.InsertDataPatient(createPatient());
        }catch (Exception e){
            log.error("insertDataPatient [2]: - error TEST {}", e.getMessage());
        }
        Map<String,Object> actual = dataProviderPostgres.selectPatientId(expected.getPersonID());

        assertEquals(actual.get("patient_id"),expected.getPersonID());
        log.debug("insertDataPatient [3]: - insert Data Patient test succeeded Postgres");

    }

    @Test
    @Tag("INSERT_NEG")
    @Order(5)
    void insertDataPatientNegative() {
        log.debug("insertDataPatientNegative() [1]: - insert Data Patient test started Postgres");
        Patient expected = createPatient();
        Map<String,Object> actual = null;
        try {
            actual = dataProviderPostgres.selectPatientId(expected.getPersonID());
        }catch (Exception e){
            log.error("insertDataPatientNegative() [2]: - error TEST {}", e.getMessage());
        }

        assertFalse(actual.get("patient_id") != expected.getPersonID());
        log.debug("insertDataPatientNegative() [3]: - insert Data Patient test Negative succeeded Postgres");

    }



    @Test
    @Tag("INSERT")
    @Order(6)
    void insertDataPreparation() {
        log.debug("insertDataPreparation [1]: - insert Data Preparation test started Postgres");
        Map<String,Object> actual = null;
        Preparation expected = createPreparation();
        try {
            dataProviderPostgres.InsertDataPreparation(expected,1,"2023-12-3");
            actual = dataProviderPostgres.selectPreparationId(expected.getPreparation_ID());
        }catch (Exception e){
            log.error("insertDataPatient [2]: - error TEST {}", e.getMessage());
        }

        assertEquals(actual.get("preparation_id"),expected.getPreparation_ID());
        log.debug("insertDataPatient [3]: - insert Data Preparation test succeeded Postgres");
    }

    @Test
    @Tag("INSERT_NEG")
    @Order(7)
    void insertDataPreparationNegative() {
        log.debug("insertDataPreparationNegative [1]: - insert Data Preparation test Negative started Postgres");
        Map<String,Object> actual = null;
        Preparation expected = createPreparation();
        try {
            actual = dataProviderPostgres.selectPreparationId(expected.getPreparation_ID());
        }catch (Exception e){
            log.error("insertDataPreparationNegative [2]: - error TEST {}", e.getMessage());
        }

        assertFalse(actual.get("preparation_id") != expected.getPreparation_ID());
        log.debug("insertDataPreparationNegative [3]: - insert Data Preparation test Negative succeeded Postgres");
    }



    @Test
    @Tag("INSERT")
    @Order(8)
    void insertDataSchedule() {
        log.debug("insertDataSchedule [1]: - insert Data Schedule test started Postgres");
        Map<String,Object> actual = null;
        Schedule expected = createSchedule();
        try {
            dataProviderPostgres.InsertDataSchedule(expected,"2023-12-3","12:23:23","13:00:00");
            actual = dataProviderPostgres.selectScheduleId(expected.getScheduleID());
        }catch (Exception e){
            log.error("insertDataSchedule [2]: - error TEST {}", e.getMessage());
        }
        assertEquals(actual.get("schedule_id"),expected.getScheduleID());
        log.debug("insertDataSchedule [3]: - insert Data Schedule test succeeded Postgres");
    }

    @Test
    @Tag("INSERT_NEG")
    @Order(9)
    void insertDataScheduleNegative() {
        log.debug("insertDataScheduleNegative [1]: - insert Data Schedule test started Postgres");
        Map<String,Object> actual = null;
        Schedule expected = createSchedule();
        try {
            actual = dataProviderPostgres.selectScheduleId(expected.getScheduleID());
        }catch (Exception e){
            log.error("insertDataScheduleNegative [2]: - error TEST {}", e.getMessage());
        }
        assertFalse(actual.get("schedule_id") != expected.getScheduleID());
        log.debug("insertDataScheduleNegative [3]: - insert Data Schedule test succeeded Postgres");
    }



    @Test
    @Tag("INSERT")
    @Order(10)
    void insertDataCalcReport() {
        log.debug("insertDataCalcReport[1]: - insert Data Calc Report test started Postgres");
        Map<String,Object> actual = null;
        CalcReport expected = createCalcReport();
        try {
            dataProviderPostgres.InsertDataCalcReport(expected);
            actual = dataProviderPostgres.selectCalcReportId(expected.getReport_ID());
        }catch (Exception e){
            log.error("insertDataCalcReport [2]: - error TEST {}", e.getMessage());
        }
        assertEquals(actual.get("report_id"),expected.getReport_ID());
        log.debug("insertDataCalcReport [3]: - insert Data Calc Report test succeeded Postgres");
    }

    @Test
    @Tag("INSERT_NEG")
    @Order(11)
    void insertDataCalcReportNegative() {
        log.debug("insertDataCalcReportNegative[1]: - insert Data Calc Report test started Postgres");
        Map<String,Object> actual = null;
        CalcReport expected = createCalcReport();
        try {
            actual = dataProviderPostgres.selectCalcReportId(expected.getReport_ID());
        }catch (Exception e){
            log.error("insertDataCalcReportNegative [2]: - error TEST {}", e.getMessage());
        }
        assertFalse(actual.get("report_id") != expected.getReport_ID());
        log.debug("insertDataCalcReportNegative [3]: - insert Data Calc Report test Negative succeeded Postgres");
    }



    void checkActualExpectedPerson(Map<String,Object> actual, Map<String, Object> expected){
        log.debug("checkActualExpectedPerson [1]: started checking object ");
        assertEquals(actual.get("status").toString() ,expected.get("Status").toString());
        assertEquals(actual.get("surname") ,expected.get("Surname"));
        assertEquals(actual.get("name") ,expected.get("Name"));
        log.debug("checkActualExpectedPerson [2]: succeeded");

    }


    @Test
    @Tag("SELECT")
    @Order(12)
    void selectDoctorId() {
        log.debug("selectDoctorId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createDoctor()); // Разные геттеры с пациентом
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectDoctorId(createDoctor().getPersonID());
        log.info("selectDoctorId [2]: actual data = {}", actual);
        log.info("selectDoctorId [3]: expected data = {}", expected);

        assertEquals(actual.get("doctor_id") ,expected.get("Person_ID"));

        checkActualExpectedPerson(actual,expected);

        log.debug("selectDoctorId [2]: TEST select data succeeded ");
    }
    @Test
    @Tag("SELECT_NEG")
    @Order(13)
    void selectDoctorIdNegative() {
        log.debug("selectDoctorIdNegative [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createDoctor());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectDoctorId(createDoctor().getPersonID());
        log.info("selectDoctorIdNegative [2]: actual data = {}", actual);
        log.info("selectDoctorIdNegative [3]: expected data = {}", expected);

        assertFalse(actual.get("doctor_id") != expected.get("Person_ID"));

        log.debug("selectDoctorIdNegative [2]: TEST select data Negative succeeded ");
    }



    @Test
    @Tag("SELECT")
    @Order(14)
    void selectPatientId() {
        log.debug("selectPatientId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createPatient());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectPatientId(createPatient().getPersonID());
        log.info("selectPatientId [2]: actual data = {}", actual);
        log.info("selectPatientId [3]: expected data = {}", expected);

        assertEquals(actual.get("patient_id") ,expected.get("Person_ID"));
        checkActualExpectedPerson(actual,expected);

        log.debug("selectDoctorId [4]: TEST select data succeeded ");
    }
    @Test
    @Tag("SELECT_NEG")
    @Order(15)
    void selectPatientIdNegative() {
        log.debug("selectPatientIdNegative [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createPatient());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectPatientId(createPatient().getPersonID());

        log.info("selectPatientIdNegative [2]: actual data = {}", actual);
        log.info("selectPatientIdNegative [3]: expected data = {}", expected);

        assertFalse(actual.get("patient_id") != expected.get("Person_ID"));

        log.debug("selectPatientIdNegative [4]: TEST select data Negative succeeded ");
    }




    @Test
    @Tag("SELECT")
    @Order(16)
    void selectPreparationId() {
        log.debug("selectPreparationId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createPreparation());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectPreparationId(createPreparation().getPreparation_ID());

        log.info("selectPatientId [2]: actual data = {}", actual);
        log.info("selectPatientId [3]: expected data = {}", expected);

        assertEquals(actual.get("preparation_id") ,expected.get("Preparation_ID"));
        assertEquals(actual.get("nameprep") ,expected.get("NamePrep"));
        log.debug("selectPreparationId [4]: TEST select data succeeded ");
    }


    @Test
    @Tag("SELECT_NEG")
    @Order(17)
    void selectPreparationIdNegative() {
        log.debug("selectPreparationIdNegative [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createPreparation());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectPreparationId(createPreparation().getPreparation_ID());

        log.info("selectPreparationIdNegative [2]: actual data = {}", actual);
        log.info("selectPreparationIdNegative [3]: expected data = {}", expected);

        assertFalse(actual.get("preparation_id") != expected.get("Preparation_ID"));
        log.debug("selectPreparationIdNegative [4]: TEST select data succeeded ");
    }


    @Test
    @Tag("SELECT")
    @Order(18)
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
    @Tag("SELECT_NEG")
    @Order(19)
    void selectScheduleIdNegative() {
        log.debug("selectScheduleIdNegative [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createSchedule());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectScheduleId(1);

        log.info("selectScheduleIdNegative [2]: actual data = {}", actual);
        log.info("selectScheduleIdNegative [3]: expected data = {}", expected);

        assertFalse(actual.get("schedule_id") != expected.get("Schedule_ID"));

        log.debug("selectScheduleIdNegative [4]: TEST select data Negative succeeded ");
    }


    @Test
    @Tag("SELECT")
    @Order(20)
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

    @Test
    @Tag("SELECT_NEG")
    @Order(21)
    void selectCalcReportIdNegative() {
        log.debug("selectCalcReportId [1]: TEST started SELECT");
        HistoryContent wrap = new HistoryContent();
        wrap.setObject(createCalcReport());
        Map<String, Object> expected = wrap.getObject();
        Map<String,Object> actual = dataProviderPostgres.selectCalcReportId(createCalcReport().getReport_ID());

        log.info("selectCalcReportId [2]: actual data = {}", actual);
        log.info("selectCalcReportId [3]: expected data = {}", expected);

        assertFalse(actual.get("report_id") != expected.get("Report_ID"));
        assertNotNull(actual);

        log.debug("selectScheduleId [4]: TEST select data succeeded ");
    }



    @Test
    @Tag("DELETE")
    @Order(22)
    void deleteDataCalcReport() {
        log.debug("deleteDataCalcReport [1]: Test started DELETE");
        Map<String,Object> actual = null;
        Integer CalcReportTest_ID = createCalcReport().getReport_ID();
        try {
            dataProviderPostgres.deleteDataCalcReport(CalcReportTest_ID);
            actual = dataProviderPostgres.selectCalcReportId(CalcReportTest_ID);
        }catch (Exception e){
            log.error("deleteDataCalcReport [2]: TEST {}",e.getMessage());
        }
        assertNull(actual.get("report_id"));
        log.debug("deleteDataCalcReport [3]:  TEST DELETE data succeeded");
    }




    @Test
    @Tag("DELETE")
    @Order(23)
    void deleteDataSchedule() {
        log.debug("deleteDataSchedule [1]: Test started DELETE");
        Map<String,Object> actual = null;
        Integer ScheduleTest_ID = createSchedule().getScheduleID();
        try {
            dataProviderPostgres.deleteDataSchedule(ScheduleTest_ID);
            actual = dataProviderPostgres.selectScheduleId(ScheduleTest_ID);
        }catch (Exception e){
            log.error("deleteDataSchedule [2]: TEST {}",e.getMessage());
        }
        assertNull(actual.get("schedule_id"));
        log.debug("deleteDataCalcReport [3]:  TEST DELETE data succeeded");
    }
    @Test
    @Tag("DELETE")
    @Order(24)
    void deleteDataPatient() {
        log.debug("deleteDataPatient [1]: Test started DELETE");
        Map<String,Object> actual = null;
        Integer PatientTest_ID = createPatient().getPersonID();
        try {
            dataProviderPostgres.deleteDataPatient(PatientTest_ID);
            actual = dataProviderPostgres.selectPatientId(PatientTest_ID);
        }catch (Exception e){
            log.error("deleteDataPatient [2]: TEST {}",e.getMessage());
        }
        assertNull(actual.get("patient_id"));
        log.debug("deleteDataPatient [3]:  TEST DELETE data succeeded");
    }
    @Test
    @Tag("DELETE")
    @Order(25)
    void deleteDataPreparation() {
        log.debug("deleteDataPreparation [1]: Test started DELETE");
        Map<String,Object> actual = null;
        Integer PreparationTest_ID = createPreparation().getPreparation_ID();
        try {
            dataProviderPostgres.deleteDataPreparation(PreparationTest_ID);
            actual = dataProviderPostgres.selectPreparationId(PreparationTest_ID);
        }catch (Exception e){
            log.error("deleteDataPreparation [2]: TEST {}",e.getMessage());
        }
        assertNull(actual.get("preparation_id"));
        log.debug("deleteDataPreparation [3]:  TEST DELETE data succeeded");
    }

    @Test
    @Tag("DELETE")
    @Order(26)
    void deleteDataDoctor() {
        log.debug("deleteDataDoctor [1]: Test started DELETE");
        Map<String,Object> actual = null;
        Integer DoctorTest_ID = createDoctor().getPersonID();
        try {
            dataProviderPostgres.deleteDataDoctor(DoctorTest_ID);
            actual = dataProviderPostgres.selectDoctorId(DoctorTest_ID);
        }catch (Exception e){
            log.error("deleteDataDoctor [2]: TEST {}",e.getMessage());
        }
        assertNull(actual.get("doctor_id"));
        log.debug("deleteDataDoctor [3]:  TEST DELETE data succeeded");

    }







}