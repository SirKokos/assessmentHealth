package ru.sfedu.assessmentHealth.api;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.assessmentHealth.Const;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.PostgresUtils;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProviderPost implements IDataProvider {
    private static final Logger log = LogManager.getLogger(DataProviderPost.class.getName());

//    String url = PropertyConfig.getPropertyValue(Const.BD_POSTGRES_HOST,Const.NAME_PROPERTY_FILE)
//            .concat(PropertyConfig.getPropertyValue(Const.BD_POSTGRES_NAME,Const.NAME_PROPERTY_FILE));
    String url = PropertyConfig.getPropertyValue(Const.BD_POSTGRES_HOST,PropertyConfig.getConfigPath())
            .concat(PropertyConfig.getPropertyValue(Const.BD_POSTGRES_NAME,PropertyConfig.getConfigPath()));

    public DataProviderPost() {
        log.debug("DataProviderPost [1]: - working constructor");
        try (Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE)
        )){
            createTables(connection);
        } catch (SQLException e) {
            log.error("DataProviderPost [2]: - Error {}",e.getMessage());
        }
        log.debug("DataProviderPost [3]: - End constructor");
    }

    protected void createTables(Connection connection){
        log.debug("createTables [1] - start working method ");
        List<String> nameTab = new ArrayList<>();
        try(Statement statement = connection.createStatement();){
            statement.executeUpdate(Const.SQL_TABLE_CREATE_DOCTOR);
            statement.executeUpdate(Const.SQL_TABLE_CREATE_PATIENT);
            statement.executeUpdate(Const.SQL_TABLE_CREATE_PREPARATION);
            statement.executeUpdate(Const.SQL_TABLE_CREATE_SCHEDULE);
            statement.executeUpdate(Const.SQL_TABLE_CREATE_CALC_REPORT);
            ResultSet resultSet = connection.getMetaData().getTables(null,null,"%", new String[]{"TABLE"});
            while (resultSet.next()) {
                nameTab.add(resultSet.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            log.error("DataProviderPostgres [2] - initialization error {}", e.getMessage());
        }
        log.debug("createTables [3]: - successfully {}" , nameTab);
    }

    /**
     * @param obj - Экземпляр класса Doctor
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertDoctor(Doctor obj) {
        log.debug("insertDoctor [1]: start working");
        StatusAnswer result = StatusAnswer.ERROR;
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
            )
        {
            PreparedStatement statement = connection.prepareStatement(Const.INSERT_DOCTOR);
            PostgresUtils.createDoctor(statement,obj).executeUpdate();
            result = StatusAnswer.OK;
        } catch (SQLException e) {
            log.debug("insertDoctor [2]: Error {}",e.getMessage());
        }
        log.debug("insertDoctor [3]: end working");
        return result;
    }

    /**
     * @param obj - Экземпляр класса Patient
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertPatient(Patient obj) {
        log.debug("insertPatient [1]: start working");
        StatusAnswer result = StatusAnswer.ERROR;
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.INSERT_PATIENT);
            PostgresUtils.createPatient(statement,obj).executeUpdate();
            result = StatusAnswer.OK;
        } catch (SQLException e) {
            log.debug("insertPatient [2]: Error {}",e.getMessage());
        }
        log.debug("insertPatient [3]: end working");
        return result;
    }

    /**
     * @param obj - Экземпляр класса Preparation
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertPreparation(Preparation obj) {
        log.debug("insertPreparation [1]: start working");
        StatusAnswer result = StatusAnswer.ERROR;
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.INSERT_PREPARATION);
            PostgresUtils.createPreparation(statement,obj).executeUpdate();
            result = StatusAnswer.OK;
        } catch (SQLException e) {
            log.debug("insertPreparation [2]: Error {}",e.getMessage());
        }
        log.debug("insertPreparation [3]: end working");
        return result;
    }

    /**
     * @param obj - Экземпляр класса Schedule
     * @return StatusAnswer(OK / ERROR)
     * Дата в формате YYYY-MM-DD
     * время начала  в формате HH-MM-SS
     * время конца в формате HH-MM-SS
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertSchedule(Schedule obj) {
        log.debug("insertSchedule [1]: start working");
        StatusAnswer result = StatusAnswer.ERROR;
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.INSERT_SCHEDULE);
            PostgresUtils.createSchedule(statement,obj).executeUpdate();
            result = StatusAnswer.OK;
        } catch (SQLException e) {
            log.debug("insertSchedule [2]: Error {}",e.getMessage());
        }
        log.debug("insertSchedule [3]: end working");
        return result;
    }

    /**
     * @param obj - Экземпляр класса CalcReport
     * @return StatusAnswer(OK / ERROR)
     * @see StatusAnswer
     */
    @Override
    public StatusAnswer insertCalcReport(CalcReport obj) {
        log.debug("insertCalcReport [1]: start working");
        StatusAnswer result = StatusAnswer.ERROR;
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.INSERT_CALC_REPORT);
            PostgresUtils.createCalcReport(statement,obj).executeUpdate();
            result = StatusAnswer.OK;
        } catch (SQLException e) {
            log.debug("insertCalcReport [2]: Error {}",e.getMessage());
        }
        log.debug("insertCalcReport [3]: end working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Doctor>
     */
    @Override
    public Optional<Doctor> selectDoctorId(Integer id) {
        log.debug("selectDoctorId [1]: start working");
        Optional<Doctor> result = Optional.empty();

        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.SELECT_DOCTOR_TO_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
//            resultSet.next();
//            System.out.println(resultSet.getString(10));

            result = Optional.of(PostgresUtils.getDoctor(resultSet));

        } catch (SQLException e) {
            log.error("selectDoctorId [2]: Error {}",e.getMessage());
        }
        log.debug("selectDoctorId [3]: end working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Patient>
     */
    @Override
    public Optional<Patient> selectPatientId(Integer id) {
        log.debug("selectPatientId [1]: start working");
        Optional<Patient> result = Optional.empty();

        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.SELECT_PATIENT_TO_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            result = Optional.of(PostgresUtils.getPatient(resultSet));
        } catch (SQLException e) {
            log.error("selectPatientId [2]: Error {}",e.getMessage());
        }
        log.debug("selectPatientId [3]: end working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Schedule>
     */
    @Override
    public Optional<Schedule> selectScheduleId(Integer id) {
        log.debug("selectScheduleId [1]: start working");
        Optional<Schedule> result = Optional.empty();

        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.SELECT_SCHEDULE_TO_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            result = Optional.of(PostgresUtils.getSchedule(resultSet));
        } catch (SQLException e) {
            log.error("selectScheduleId [2]: Error {}",e.getMessage());
        }
        log.debug("selectScheduleId [3]: end working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<Preparation>
     */
    @Override
    public Optional<Preparation> selectPreparationId(Integer id) {
        log.debug("selectPreparationId [1]: start working");
        Optional<Preparation> result = Optional.empty();

        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.SELECT_PREPARATION_TO_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            result = Optional.of(PostgresUtils.getPreparation(resultSet));
        } catch (SQLException e) {
            log.error("selectPreparationId [2]: Error {}",e.getMessage());
        }
        log.debug("selectPreparationId [3]: end working");
        return result;
    }

    /**
     * @param id - id объекта, который хотим найти
     * @return Optional<CalcReport>
     */
    @Override
    public Optional<CalcReport> selectCalcReport(Integer id) {
        log.debug("selectCalcReport [1]: start working");
        Optional<CalcReport> result = Optional.empty();

        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.SELECT_CALC_REPORT_TO_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            result = Optional.of(PostgresUtils.getCalcReport(resultSet));
        } catch (SQLException e) {
            log.error("selectCalcReport [2]: Error {}",e.getMessage());
        }
        log.debug("selectCalcReport [3]: end working");
        return result;
    }

    /**
     * Метод получает список всех врачей
     *
     * @return Optional<List < Doctor>>
     */
    @Override
    public Optional<List<Doctor>> selectAllDoctor() {
        log.debug("selectAllDoctor [1]: start working");
        List<Doctor> result = new ArrayList<>();
        List<Integer> listIdDoctor = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.SELECT_ALL_DOCTOR);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listIdDoctor.add(resultSet.getInt(1));
            }
            for(Integer i:listIdDoctor){result.add(selectDoctorId(i).get());}

        } catch (SQLException e) {
            log.error("selectAllDoctor [2]: Error {}",e.getMessage());
        }
        log.debug("selectAllDoctor [3]: end working");
        return Optional.of(result);
    }

    /**
     * @param id который нужно удалить Врача
     * @return StatusAnswer
     */
    @Override
    public StatusAnswer deleteDoctor(Integer id) {
        log.debug("deleteDoctor [1]: start working");
        StatusAnswer result = StatusAnswer.ERROR;
        try(Connection connection = DriverManager.getConnection(url,
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_USER,Const.NAME_PROPERTY_FILE),
                PropertyConfig.getPropertyValue(Const.BD_POSTGRES_PASSWORD,Const.NAME_PROPERTY_FILE))
        )
        {
            PreparedStatement statement = connection.prepareStatement(Const.DELETE_DOCTOR_ID);
            statement.setInt(1,id);
            statement.executeUpdate();
            result = StatusAnswer.OK;
        } catch (SQLException e) {
            log.debug("deleteDoctor [2]: Error {}",e.getMessage());
        }
        log.debug("deleteDoctor [3]: end working");
        return result;
    }
}
