package ru.sfedu.assessmentHealth.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.api.BaseTest;


import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostgresUtilsTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(PostgresUtilsTest.class.getName());
    String url = PropertyConfig.getPropertyValue(Const.BD_POSTGRES_HOST,Const.NAME_PROPERTY_FILE)
            .concat(PropertyConfig.getPropertyValue(Const.BD_POSTGRES_NAME,Const.NAME_PROPERTY_FILE));

    PreparedStatement statement_1;
    PreparedStatement statement_2;
    PreparedStatement statement_3;
    PreparedStatement statement_4;
    PreparedStatement statement_5;
    {
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))) {
            statement_1 = connection.prepareStatement(Const.INSERT_DOCTOR);
            statement_2 = connection.prepareStatement(Const.INSERT_PATIENT);
            statement_3 = connection.prepareStatement(Const.INSERT_PREPARATION);
            statement_4 = connection.prepareStatement(Const.INSERT_SCHEDULE);
            statement_5 = connection.prepareStatement(Const.INSERT_CALC_REPORT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createDoctor() throws SQLException {
        log.debug("createDoctor [1]: - Start working");
        log.debug("createDoctor [2]: {}", PostgresUtils.createDoctor(statement_1,getDoctor()));

    }

    @Test
    void createPatient() throws SQLException {
        log.debug("createPatient [1]: - Start working");
        log.debug("createPatient [2]: {}", PostgresUtils.createPatient(statement_2,getPatient()));

    }

    @Test
    void createPreparation() throws SQLException {
        log.debug("createPreparation [1]: - Start working");
        log.debug("createPreparation [2]: {}", PostgresUtils.createPreparation(statement_3,getPreparation()));

    }

    @Test
    void createSchedule() throws SQLException {
        log.debug("createSchedule [1]: - Start working");
        log.debug("createSchedule [2]: {}", PostgresUtils.createSchedule(statement_4,getSchedule()));

    }

    @Test
    void createCalcReport() throws SQLException {
        log.debug("createCalcReport [1]: - Start working");
        log.debug("createCalcReport [2]: {}", PostgresUtils.createCalcReport(statement_5,getCalcReport()));

    }
    PreparedStatement statement_select_1;
    PreparedStatement statement_select_2;
    PreparedStatement statement_select_3;
    PreparedStatement statement_select_4;
    PreparedStatement statement_select_5;
    {
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))) {
            statement_select_1 = connection.prepareStatement(Const.SELECT_DOCTOR_TO_ID);
            statement_select_2 = connection.prepareStatement(Const.SELECT_PATIENT_TO_ID);
            statement_select_3 = connection.prepareStatement(Const.SELECT_PREPARATION_TO_ID);
            statement_select_4 = connection.prepareStatement(Const.SELECT_SCHEDULE_TO_ID);
            statement_select_5 = connection.prepareStatement(Const.SELECT_CALC_REPORT_TO_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetDoctor() throws SQLException {
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))) {
            statement_select_1 = connection.prepareStatement(Const.SELECT_DOCTOR_TO_ID);
            statement_select_1.setInt(1,1);
            ResultSet resultSet = statement_select_1.executeQuery();
            System.out.println(PostgresUtils.getDoctor(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testGetPreparation() {
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))) {
            statement_select_1 = connection.prepareStatement(Const.SELECT_PREPARATION_TO_ID);
            statement_select_1.setInt(1,1);
            ResultSet resultSet = statement_select_1.executeQuery();
            System.out.println(PostgresUtils.getPreparation(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetSchedule() {
    }

    @Test
    void testGetPatient() {
    }

    @Test
    void testGetCalcReport() {
    }
}