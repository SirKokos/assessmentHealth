package ru.sfedu.assessmentHealth.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.PostgresUtils;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DataProviderPostTest extends BaseTest{

    public static final Logger log = LogManager.getLogger(DataProviderPostTest.class.getName());

    DataProviderPost dataProviderPostgres = new DataProviderPost();


    @Test
    @Order(1)
    void insertDoctor() {
        log.debug("insertDataDoctor [1]: - start working test ");
        Doctor d = getDoctor();
        StatusAnswer actual = dataProviderPostgres.insertDoctor(d);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertDataDoctor [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(2)
    void insertPatient() {
        log.debug("insertPatient [1]: - start working test ");
        Patient p = getPatient();
        StatusAnswer actual = dataProviderPostgres.insertPatient(p);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertPatient [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(3)
    void insertPreparation() {
        log.debug("insertPreparation [1]: - start working test ");
        Preparation p = getPreparation();
        StatusAnswer actual = dataProviderPostgres.insertPreparation(p);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertPreparation [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(4)
    void insertSchedule() {
        log.debug("insertSchedule [1]: - start working test ");
        Schedule s = getSchedule();
        StatusAnswer actual = dataProviderPostgres.insertSchedule(s);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertSchedule [2]: - working end succeeded status {}",actual);
    }

    @Test
    @Order(5)
    void insertCalcReport() {
        log.debug("insertCalcReport [1]: - start working test ");
        CalcReport s = getCalcReport();
        StatusAnswer actual = dataProviderPostgres.insertCalcReport(s);
        assertEquals(actual,StatusAnswer.OK);
        log.debug("insertCalcReport [2]: - working end succeeded status {}",actual);
    }
//
    @Test
    @Order(6)
    void selectDoctorId() {
        log.debug("selectDoctorId [1]: - start working test");
        Optional<Doctor> actual =  dataProviderPostgres.selectDoctorId(1);
        assertEquals(actual.get(),getDoctor());
        log.debug("selectDoctorId [2]: - end working test");
    }

    @Test
    @Order(7)
    void selectPatientId() {
        log.debug("selectPatientId [1]: - start working test");
        Optional<Patient> actual =  dataProviderPostgres.selectPatientId(1);
        assertEquals(actual.get(),getPatient());
        log.debug("selectPatientId [2]: - end working test");
    }

    @Test
    @Order(8)
    void selectScheduleId() {
        log.debug("selectScheduleId [1]: - start working test");
        Optional<Schedule> actual =  dataProviderPostgres.selectScheduleId(1);
        assertEquals(actual.get(),getSchedule());
        log.debug("selectScheduleId [2]: - end working test");
    }

    @Test
    @Order(9)
    void selectPreparationId() {
        log.debug("selectPreparationId [1]: - start working test");
        Optional<Preparation> actual =  dataProviderPostgres.selectPreparationId(1);
        assertEquals(actual.get(),getPreparation());
        log.debug("selectPreparationId [2]: - end working test");
    }

    @Test
    @Order(10)
    void selectCalcReport() {
        log.debug("selectCalcReport [1]: - start working test");
        Optional<CalcReport> actual =  dataProviderPostgres.selectCalcReport(1);
        assertEquals(actual.get(),getCalcReport());
        log.debug("selectCalcReport [2]: - end working test");

    }

    @Test
    @Order(11)
    void deleteDoctor() {
        log.debug("deleteDoctor [1]: - start working test");
        StatusAnswer actual =  dataProviderPostgres.deleteDoctor(1);
        assertTrue(dataProviderPostgres.selectDoctorId(1).isEmpty());
        log.debug("deleteDoctor [2]: - end working test");

    }

    @Test
    @Order(12)
    void selectDoctorIdNegativity() {
        log.debug("selectDoctorIdNegativity [1]: - start working test");
        Optional<Doctor> actual =  dataProviderPostgres.selectDoctorId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectDoctorIdNegativity [2]: - end working test");
    }


    /**
     * Метод который всатвляет обратно id = 1
     * @return StatusAnswer
     * @see .selectDoctorIdNegativity
     *
     */
    @Test
    @Order(13)
    void insertDoctorTestData(){
        String url = PropertyConfig.getPropertyValue(Const.BD_POSTGRES_HOST,Const.NAME_PROPERTY_FILE)
                .concat(PropertyConfig.getPropertyValue(Const.BD_POSTGRES_NAME,Const.NAME_PROPERTY_FILE));
        log.debug("insertDoctorTestData [1]: start working");
        StatusAnswer result = StatusAnswer.ERROR;
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.TEST_INSERT_DOCTOR);
            statement.executeUpdate();
            result = StatusAnswer.OK;
        } catch (SQLException e) {
            log.debug("insertDoctorTestData [2]: Error {}",e.getMessage());
        }
        log.debug("insertDoctorTestData [3]: end working");
    }

    @Test
    @Order(14)
    void selectPatientIdNegativity() {
        log.debug("selectPatientIdNegativity [1]: - start working test");
        Optional<Patient> actual =  dataProviderPostgres.selectPatientId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectPatientIdNegativity [2]: - end working test");
    }

    @Test
    @Order(15)
    void selectScheduleIdNegativity() {
        log.debug("selectScheduleIdNegativity [1]: - start working test");
        Optional<Schedule> actual =  dataProviderPostgres.selectScheduleId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectScheduleIdNegativity [2]: - end working test");
    }

    @Test
    @Order(16)
    void selectPreparationIdNegativity() {
        log.debug("selectPreparationIdNegativity [1]: - start working test");
        Optional<Preparation> actual =  dataProviderPostgres.selectPreparationId(0);
        assertTrue(actual.isEmpty());
        log.debug("selectPreparationIdNegativity [2]: - end working test");
    }

    @Test
    @Order(17)
    void selectCalcReportNegativity() {
        log.debug("selectCalcReportNegativity [1]: - start working test");
        Optional<CalcReport> actual =  dataProviderPostgres.selectCalcReport(0);
        assertTrue(actual.isEmpty());
        log.debug("selectCalcReportNegativity [2]: - end working test");

    }

    @Test
    @Order(18)
    void selectAllDoctor() {
        log.debug("selectAllDoctor [1]: - start working test");
        Optional<List<Doctor>> actual =  dataProviderPostgres.selectAllDoctor();
        System.out.println(actual.get());
        assertFalse(actual.isEmpty());
        log.debug("selectAllDoctor [2]: - end working test");

    }

}